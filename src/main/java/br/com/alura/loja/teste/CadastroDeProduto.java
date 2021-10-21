package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto(); 
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);

		Produto p = produtoDao.buscarPorID(1l);
		
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();		
		todos.forEach(p1 -> System.out.println(p1.getNome()));
		
		List<Produto> porNome = produtoDao.buscarPorNome("Xiaomi Redmi");
		porNome.forEach(p2 -> System.out.println(p2.getNome()));
		
		List<Produto> porCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		porCategoria.forEach(p3 -> System.out.println(p3.getNome()));
				
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preco do produto " + precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("Xiaomi Redmi", "Muito Legal", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);

		em.getTransaction().commit();

		em.close();
	}

}
