/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exemplo.controllers;
import java.util.List;
import exemplo.beans.Contato;
import exemplo.dao.ContatoDao;
import exemplo.infra.Publico;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import static br.com.caelum.vraptor.view.Results.*;

@Resource
public class ContatosController {
	
	private ContatoDao dao;
	private final Result result;
	private final Validator validator;
	
	public ContatosController(ContatoDao dao, Result result, Validator validator) {
		this.dao       = dao;
		this.result    = result;
		this.validator = validator;
	}
	
	@Publico @Get @Path("/contatos")	
	public List<Contato> lista() {					
		return dao.listaTudo();
	}
	
	@Post @Path("/contatos")
	public void adiciona(Contato contato) {
		validator.validate(contato);
		validator.onErrorUsePageOf(ContatosController.class).form();
		
		dao.salva(contato);
		result.redirectTo(ContatosController.class).lista();		
	}
	
	@Get @Path("/contatos/novo")
	public void form() { }

	@Get @Path("/contatos/{id}")
	public Contato edita(long id) {
		return dao.carrega(id);
	}
	
	@Delete @Path("/contatos/{id}")	
	public void remove(long id) {
		Contato contato = dao.carrega(id);
		dao.remove(contato);
		result.redirectTo(ContatosController.class).lista();		
	}		


	@Put @Path("/contatos/{contato.id}")
	public void atualiza(Contato contato) {
		dao.atualiza(contato);
		result.redirectTo(ContatosController.class).lista();		
	}
	
	public List<Contato> busca(String nome) {
		return dao.busca(nome);
	}
	@Get @Path("/contatos/busca.json")
	public void buscaJson(String q) {
		System.out.println("nome...:"+q);
		result.use(json()).withoutRoot().from(dao.busca(q)).serialize();
	}
}
