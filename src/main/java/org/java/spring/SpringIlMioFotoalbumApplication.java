package org.java.spring;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Contact;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.ContactService;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	public void run(String... args) throws Exception {
	
		Category c1 = new Category("Ritratti");
		Category c2 = new Category("Paesaggi");
		Category c3 = new Category("Architettura");
		Category c4 = new Category("Street Photography");
		Category c5 = new Category("Natura Morta");
		Category c6 = new Category("Macrofotografia");
		Category c7 = new Category("Fotografia Astronomica");
		Category c8 = new Category("Fotografia Documentaristica");
		Category c9 = new Category("Fotografia di Moda");
		Category c10 = new Category("Fotografia Culinarie");
		categoryService.save(c1);
		categoryService.save(c2);
		categoryService.save(c3);
		categoryService.save(c4);
		categoryService.save(c5);
		categoryService.save(c6);
		categoryService.save(c7);
		categoryService.save(c8);
		categoryService.save(c9);
		categoryService.save(c10);
		
		
		Role r1 = new Role("ADMIN");
		Role r2 = new Role("SUPER-ADMIN");
		roleService.save(r1);
		roleService.save(r2);
		
		String pws1 = AuthConf.passwordEncoder().encode("password-1");
		String pws2 = AuthConf.passwordEncoder().encode("password-2");
		String pws3 = AuthConf.passwordEncoder().encode("password-3");
		User u1 = new User("utente-1", pws1, r1);
		User u2 = new User("utente-2", pws2, r1);
		User u3 = new User("utente-3", pws3, r2);
		userService.save(u1);
		userService.save(u2);
		userService.save(u3);
		
		
		Photo p1 = new Photo("albero morente", "La foto ritrae un albero morente avvolto in un'atmosfera di malinconia e decadimento. Le foglie, un tempo rigogliose e vibranti, ora appaiono disseccate e ingiallite, pronte a lasciare definitivamente i rami spogli. La corteccia mostra segni di deperimento, creando un contrasto visivo tra la forza della natura e la sua inevitabile fragilità.", "https://images.unsplash.com/photo-1610621062045-ef5f7201bb3f?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmluZSUyMGFydCUyMHBob3RvZ3JhcGh5fGVufDB8fDB8fHww", false, u1, c1, c3, c6);
		Photo p2 = new Photo("uomo con valigia", " La valigia, logora ma carica di significato, poggia sulla superficie, testimone di viaggi passati o forse pronta per nuove avventure. La scena cattura un momento di transizione e di attesa, lasciando spazio a molteplici interpretazioni sulla storia e sul destino di questo viaggiatore. ", "https://shotkit.com/wp-content/uploads/2021/03/fine-art-photography.jpg", false, u1, c5, c8, c10);
		Photo p3 = new Photo("zebra", "La foto cattura l'essenza affascinante di una zebra in bianco e nero, mostrando la sua bellezza unica nella natura. La pelliccia striata di bianco e nero è chiaramente visibile, creando un motivo distintivo che si snoda in un armonioso contrasto.", "https://assets-global.website-files.com/60e4d0d0155e62117f4faef3/621d0551187b0816a87b197b_01-vortex.jpeg", true, u2, c4, c7, c3, c9);
		Photo p4 = new Photo("terra e mare", "La fotografia cattura la serenità e l'isolamento di una scena marina in bianco e nero. La spiaggia si estende deserta, priva di presenze umane, creando un senso di tranquillità e pace. Le onde del mare lambiscono delicatamente la riva, lasciando impronte sabbiose sulla superficie della spiaggia.", "https://m.media-amazon.com/images/I/71JOXVVYfEL._UF894,1000_QL80_.jpg", true, u2, c5, c8, c3, c4, c1);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		
		
		Contact contact1 = new Contact("Franco", "franco@gmail.com", "Buongiorno voglio acquistare la tua foto");
		Contact contact2 = new Contact("Massimiliano", "massimiliano@gmail.com", "Quanto lo fai? A meno di 2$ non lo pago");
		contactService.save(contact1);
		contactService.save(contact2);
	}
}