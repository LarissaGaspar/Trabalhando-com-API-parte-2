package com.venda.venda.view.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.venda.venda.model.Venda;
import com.venda.venda.service.VendaService;
import com.venda.venda.view.model.CadVendaDto;
import com.venda.venda.view.model.VendaDto;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException.FeignClientException;

@RestController
@RequestMapping
public class VendaController {

    @Autowired
    private VendaService servico;

    @Autowired
    private ProdutoFeignClient produtoFeignClient;

    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas(){
        List<Venda> vendas = servico.listarTodas();
        return new ResponseEntity<>(vendas,HttpStatus.OK);
    }

    @PostMapping("/cadastrarvendas")//retornar 1 venda
    public ResponseEntity<VendaDto> cadastrar(@RequestBody @Valid CadVendaDto dto) {//por o Min
        Venda venda = Venda.from(dto);//na classe venda
        //venda de que produtos? verificar ids
        String produtoId = venda.getIdProduto();

        try {
            if(produtoFeignClient.obterPorId(produtoId)==null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (FeignClientException e) {
            if(e.status()==404){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                throw e;
            }
        }

        //verificar se tem produto suficiente no estoque
        Integer qtdVendida = venda.getQtdVendida();
        Object response=produtoFeignClient.saida(produtoId, qtdVendida);

        if(response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        venda = servico.adicionar(venda);
        return new ResponseEntity<>(VendaDto.from(venda), HttpStatus.CREATED);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Venda venda;
    long miliInicial;
    long miliFinal;
    long miliEncontrado;
    Date data1;
    Date data2;
    Date data3;
    List<Venda> vendasDoPeriodo = new ArrayList<>();
    ArrayList<Double> mediaPeriodo = new ArrayList<>();

    @GetMapping("/data{dataI}/{dataF}")
    public ResponseEntity<List<Venda>> porData(@PathVariable String dataI, @PathVariable String dataF) {
        List<Venda> vendas = servico.listarTodas();
        
        //montar datas do pathvariable
        //colocar barras
        StringBuilder sb1 = new StringBuilder(dataI);
        sb1 = sb1.insert(2,"/");
        sb1 = sb1.insert(5,"/");

        StringBuilder sb2 = new StringBuilder(dataF);
        sb2 = sb2.insert(2,"/");
        sb2 = sb2.insert(5,"/");

        //formata para Date e passa para milissegundo
        try {
            data1 = sdf.parse(sb1.toString());
            data2 = sdf.parse(sb2.toString());
            miliInicial = data1.getTime();
            miliFinal = data2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //montar datas vindas do banco
        //reseta a lista a cada pesquisa
        vendasDoPeriodo.clear();
        for(Venda v:vendas){
            //obter data de cada produto do banco
            String resposta = v.getDataVenda().toString();

            //colocar barras
            StringBuilder sb3 = new StringBuilder(resposta);
            sb3 = sb3.insert(2,"/");
            sb3 = sb3.insert(5,"/");

            //formata para tipo Date e passa para milissegundo
            try {
                data3 = sdf.parse(sb3.toString());
                miliEncontrado = data3.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //comparar milissegundos
            if(miliEncontrado>=miliInicial && miliEncontrado<=miliFinal){
                vendasDoPeriodo.add(v);
                mediaPeriodo.add(v.getVlrUnitario()*v.getQtdVendida());
            }else{
                return new ResponseEntity<>(vendasDoPeriodo,HttpStatus.NOT_FOUND);
            }
        }
        //fazer rodapé
        //////////////////////////////////
        //MEDIA VENDAS DO PERIODO
        //////////////////////////////////
        System.out.print("Média das vendas para este período:");
        double somaTotais=0.0;
        for(Double valores:mediaPeriodo){
            somaTotais=somaTotais+valores;//os totais serao somados
        }
        System.out.println(somaTotais/mediaPeriodo.size()+"\n\n");

        return new ResponseEntity<>(vendasDoPeriodo,HttpStatus.OK);
    }

    /*Buscando apartir de 1 data
    List<Venda> vendasDoPeriodo2 = new ArrayList<>();
    @GetMapping("/data{data}")//para obter produto por data
    public ResponseEntity<List<Venda>> obterPorData(@PathVariable String data) {
        List<Venda> vendas = servico.listarTodas();
        
        vendasDoPeriodo2.clear();
        for(Venda v:vendas){
            if (v.getDataVenda().toString().equals(data)) {
                vendasDoPeriodo2.add(v);
            }
        }
        System.out.println(vendasDoPeriodo2.size());
        return new ResponseEntity<>(vendasDoPeriodo2,HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Venda> deletar(@PathVariable String id){
        servico.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
