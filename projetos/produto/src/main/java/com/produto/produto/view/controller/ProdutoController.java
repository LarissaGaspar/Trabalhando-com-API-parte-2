package com.produto.produto.view.controller;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//eureka e gateway no dashboard
//venda e produto CTRL+SHIFT+P

import javax.validation.Valid;

import com.produto.produto.model.Produto;
import com.produto.produto.service.ProdutoService;
import com.produto.produto.view.model.CadProdutoDto;
import com.produto.produto.view.model.ProdutoDto;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping//no eureka e como se tivesse um ("produtos") aki
public class ProdutoController {
    @Autowired
    private ProdutoService servico;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos(){
        List<Produto> produtos = servico.listarTodos();

        //fazer rodapé
        //////////////////////////////////
        //MEDIA MAXIMO MINIMO
        //////////////////////////////////
        DoubleSummaryStatistics resumo = produtos.stream()
            .collect(Collectors.summarizingDouble(Produto::getValor));

        System.out.println("Valor Mínimo: " + resumo.getMin());
        System.out.println("Média dos valores: " + resumo.getAverage());
        System.out.println("Valor Máximo: " + resumo.getMax());

        return new ResponseEntity<>(produtos,HttpStatus.OK);
    }

    @PostMapping("/cadastrarprodutos")
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid CadProdutoDto dto) {
        Produto produto = Produto.from(dto);//na classe produto
        produto = servico.adicionar(produto);
        return new ResponseEntity<>(ProdutoDto.from(produto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> obterPorId(@PathVariable String id) {
        Optional<Produto> produto = servico.obterPorId(id);

        if (produto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ProdutoDto.from(produto.get()), HttpStatus.OK);
    }

    Produto produto;
    @GetMapping("/{id}/{valor}")
    public ResponseEntity<ProdutoDto> saida(@PathVariable String id, @PathVariable Integer valor) {

        Optional<Produto> opt = servico.obterPorId(id);

        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produto = opt.get();

        if(produto.getQtdEstoque()>valor){
            produto.setQtdEstoque(valor);
            produto = servico.adicionar(produto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
