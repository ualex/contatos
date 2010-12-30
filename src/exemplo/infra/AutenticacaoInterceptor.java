package exemplo.infra;

import java.lang.annotation.Annotation;

import exemplo.beans.UsuarioWeb;
import exemplo.controllers.UsuariosController;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class AutenticacaoInterceptor implements Interceptor{
	
	private final UsuarioWeb usuarioWeb;
	private final Result result;
	
	public AutenticacaoInterceptor(UsuarioWeb usuario, Result result) {
		this.usuarioWeb = usuario;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		return !this.usuarioWeb.isLogado() && !method.containsAnnotation(Publico.class);
	}

	public void intercept(InterceptorStack arg0, ResourceMethod arg1,
			Object arg2) throws InterceptionException {
		
		result.redirectTo(UsuariosController.class).loginForm();
		
	}


}
