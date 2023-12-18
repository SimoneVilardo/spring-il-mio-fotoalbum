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
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category("Ritratti");
		Category cat2 = new Category("Paesaggi");
		Category cat3 = new Category("Architettura");
		Category cat4 = new Category("Street Photography");
		Category cat5 = new Category("Natura Morta");
		Category cat6 = new Category("Macrofotografia");
		Category cat7 = new Category("Fotografia Astronomica");
		Category cat8 = new Category("Fotografia Documentaristica");
		Category cat9 = new Category("Fotografia di Moda");
		Category cat10 = new Category("Fotografia Culinarie");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		categoryService.save(cat6);
		categoryService.save(cat7);
		categoryService.save(cat8);
		categoryService.save(cat9);
		categoryService.save(cat10);
		
		
		Photo ph1 = new Photo("albero morente", "La foto ritrae un albero morente avvolto in un'atmosfera di malinconia e decadimento. Le foglie, un tempo rigogliose e vibranti, ora appaiono disseccate e ingiallite, pronte a lasciare definitivamente i rami spogli. La corteccia mostra segni di deperimento, creando un contrasto visivo tra la forza della natura e la sua inevitabile fragilità.", "https://images.unsplash.com/photo-1610621062045-ef5f7201bb3f?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmluZSUyMGFydCUyMHBob3RvZ3JhcGh5fGVufDB8fDB8fHww", true, cat1, cat3, cat6);
		Photo ph2 = new Photo("uomo con valigia", " La valigia, logora ma carica di significato, poggia sulla superficie, testimone di viaggi passati o forse pronta per nuove avventure. La scena cattura un momento di transizione e di attesa, lasciando spazio a molteplici interpretazioni sulla storia e sul destino di questo viaggiatore. ", "https://shotkit.com/wp-content/uploads/2021/03/fine-art-photography.jpg", true, cat5,cat8,cat10);
		Photo ph3 = new Photo("zebra", "La foto cattura l'essenza affascinante di una zebra in bianco e nero, mostrando la sua bellezza unica nella natura. La pelliccia striata di bianco e nero è chiaramente visibile, creando un motivo distintivo che si snoda in un armonioso contrasto.", "https://assets-global.website-files.com/60e4d0d0155e62117f4faef3/621d0551187b0816a87b197b_01-vortex.jpeg", true, cat4, cat7,cat3,cat9);
		Photo ph4 = new Photo("terra e mare", "La fotografia cattura la serenità e l'isolamento di una scena marina in bianco e nero. La spiaggia si estende deserta, priva di presenze umane, creando un senso di tranquillità e pace. Le onde del mare lambiscono delicatamente la riva, lasciando impronte sabbiose sulla superficie della spiaggia.", "https://m.media-amazon.com/images/I/71JOXVVYfEL._UF894,1000_QL80_.jpg", true, cat5,cat8,cat3,cat4,cat1);
		
		
		photoService.save(ph1);
		photoService.save(ph2);
		photoService.save(ph3);
		photoService.save(ph4);
		
		Contact contact1 = new Contact("Franco", "franco@gmail.com", "Buongiorno voglio acquistare la tua foto");
        Contact contact2 = new Contact("Massimiliano", "massimiliano@gmail.com", "Quanto lo fai? A meno di 2$ non lo pago");
        
        contactService.save(contact1);
        contactService.save(contact2);
		
		Role r1 = new Role("ADMIN");
		roleService.save(r1);

		String pws1 = AuthConf.passwordEncoder().encode("password-1");
		userService.save(new User("utente-1", pws1, r1));
		
	}

}
