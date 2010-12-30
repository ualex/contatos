package exemplo.controllers;

import exemplo.beans.Usuario;
import exemplo.beans.UsuarioWeb;
import exemplo.dao.UsuarioDao;
import exemplo.infra.Publico;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class UsuariosController {
	
	private UsuarioDao dao;
	private Result result;
	private Validator validator;
	private final UsuarioWeb usuarioWeb;
	
	public UsuariosController(UsuarioDao dao, Result result, Validator validator, UsuarioWeb usuarioWeb) {	
		this.dao 	   = dao;
		this.result    = result;
		this.usuarioWeb = usuarioWeb;
		this.validator = validator;
	}

	@Publico @Get @Path("/usuarios/novo")
	public void form() { }

	@Publico @Post @Path("/usuarios")
	public void usuarios(Usuario usuario) {
		if (dao.existeUsuario(usuario)) {
			validator.add(new ValidationMessage("Login já existe","usuario.login"));
		}
		validator.validate(usuario);
		validator.onErrorUsePageOf(UsuariosController.class).form();
		
		dao.salva(usuario);
		result.redirectTo(ContatosController.class).lista();	
	}
	
	@Publico @Get @Path("/usuarios/login")
	public void loginForm() {	}
	
	@Publico @Post @Path("/usuarios/login")
	public void login(Usuario usuario) {	
		Usuario carregado = dao.carrega(usuario); 
		
		if (carregado == null) {
			validator.add(new ValidationMessage("Login e/ou senha invalido", "usuario.login"));
		}
		usuarioWeb.login(carregado);
		
		validator.onErrorUsePageOf(UsuariosController.class).loginForm();
		result.redirectTo(ContatosController.class).lista();
	}	
	@Publico @Path("/usuarios/logout")
	public void logout() {
		usuarioWeb.logout();
		result.redirectTo(ContatosController.class).lista();
	}
}
