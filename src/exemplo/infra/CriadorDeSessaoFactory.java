package exemplo.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CriadorDeSessaoFactory implements ComponentFactory<SessionFactory>{
	
	private SessionFactory factory;
	
	@PostConstruct
	public void abre() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();		
		factory = configuration.buildSessionFactory();	
	}
	@PreDestroy
	public void fecha() {
		this.factory.close();
	}
	public SessionFactory getInstance() {		
		return factory;
	}

}
