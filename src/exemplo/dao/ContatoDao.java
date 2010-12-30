package exemplo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import exemplo.beans.Contato;


@Component
public class ContatoDao {
	private Session session;
	
	public ContatoDao(Session session) {
		this.session = session;
	}
	public void salva(Contato contato) {
		Transaction tx = session.beginTransaction();
		session.save(contato);
		tx.commit();
	}
	
	public void atualiza(Contato contato) {
		Transaction tx = session.beginTransaction();
		session.update(contato);
		tx.commit();
	}	

	public List<Contato> listaTudo() {
		return this.session.createCriteria(Contato.class).list();
	}
	public Contato carrega(long id) {		
		return (Contato)session.load(Contato.class, id);
	}
	public void remove(Contato contato) {
		Transaction tx = session.beginTransaction();
		session.delete(contato);		
		tx.commit();
	}
	public List<Contato> busca(String nome) {
		return (List<Contato>) session.createCriteria(Contato.class).add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE)).list();
	}
}
