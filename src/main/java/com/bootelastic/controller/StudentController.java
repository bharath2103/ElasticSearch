package com.bootelastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootelastic.adapters.AdapterTypeEnum;
import com.bootelastic.model.FileModel;
import com.bootelastic.model.Student;
import com.bootelastic.service.AlfrescoService;
import com.bootelastic.service.FileService;
import com.bootelastic.service.StudentService;

@CrossOrigin
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired 
	private FileService fileService;		
	
	@Autowired
	private AlfrescoService alfrescoService;
	
	@PostMapping(value="/savestudent/{adapter}")
	@ResponseBody
	public Student saveStudent(@PathVariable("adapter") AdapterTypeEnum adapter, @RequestBody Student student)
	{
		return studentService.save(student, adapter);
	}
	
	@PostMapping(value="/saveFile/{adapter}")
	@ResponseBody
	public FileModel saveFile(@PathVariable("adapter") AdapterTypeEnum adapter, @RequestBody FileModel fileModel)
	{
		return fileService.save(fileModel, adapter);
	}
	
	@GetMapping(value="/deleteallstudents/{adapter}")
	@ResponseBody
	public void deleteAllStudents(@PathVariable("adapter") AdapterTypeEnum adapter) {
		studentService.deleteAll(adapter);
	}
	
	@GetMapping(value="/deleteFile/{adapter}")
	@ResponseBody
	public void deleteFile(@PathVariable("adapter") AdapterTypeEnum adapter) {
		fileService.deleteAll(adapter);
	}

	@GetMapping(value= "/fresco")
	@ResponseBody
	public void persistFileAlfresco() {
		alfrescoService.createFileNFolder();
	}
	/*	@Autowired
	private MongoConfig mongoConfig;*/
	
	/*	@Autowired 
	private DocumentsService dopublic static void main(String[] args) {
    	System.out.println(mongoConfig.mongoDatabase());
//		System.out.println(mong);
	}
cumentsService;
	 */
/*	@GetMapping(value="/fetchallstudents")
	@ResponseBody
	public List<StudentElast
	ic> fetchAllStudents() {
		return studentService.fetchAll();
	}*/

/*	@GetMapping(value="/deletestudent/{studentid}")
	@ResponseBody
	private ServiceValidator serviceValidator;
	public void deleteStudent(@PathVariable("studentid") String studentid) {
		studentService.deleteById(studentid);
	}*/

	
/*	
	@GetMapping(value = "/fetchstudentsbyid/{id}")
	public void getPetById(@PathVariable("id") String id) {
	  studentService.fetchByID(id);
	}*/
	
/*	@GetMapping(value="/testConfig")
	public String testConfig() {
		System.out.println(mongoConfig.);
		return "testConfig";
	}*/

/*	@PostMapping(value="/updatestudent")
	@ResponseBody
	public void updateStudent(@RequestBody Student student) {
		studentServic		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);
e.update(student);
	}*/
	
/*	@GetMapping(value="/sample/{adapter}")
	@ResponseBody
	public Striimport org.bson.Document;ng sample(@PathParam("adapter") String sample)
	{
		return sample;
	}*/
	
	/*	@PostMapping(value="/saveDocuments")
	public void saveDocuments(@RequestBody Documents documents)
	{
		documentsService.sav@ResponseBodye(documents);
	}

	@PostMapping(value="/deleteDocuments")
	public void deleteDocuments(@RequestBody Docume	//--------------Mongo-----------------/nts documents)
	{
		documentsService.delete(documents);
	}

	@GetMapping(value="/deleteDocumentsByID")
	public void deleteDocumentsByID(@RequestParam String documentsID)
	{
		documentsService.deleteByID(documentsID);
	}

	@PostMapping(value="/api/v1/products", produces = MediaType.ALL_VALUE)
	public void sampleGreet(@RequestBody DBFileStore dbFileStore)
	{
		System.out.println("Sample HMongoDatabaseello"+dbFileStore);
	}*/		


	/*	public static void m@ResponseBodyain(String[] args)
	{
		Student student = new Student();
		student.setNam		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);
e("vinod");		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);

		student.setAge("20");
		student.setCity("Coimbatore");
		student.setCountry("india");
		System.out.println("God is great");

		ObjectMapper Obj = new ObjectMapper(); 
		try { 

			// get Oragani	@Idsation object as a json string 
			String jsonStr = Obj.writeValueAsString(student); 

			// Displaying JSON String 
			System.out.println(jsonStr); 
		} 
mongoDatabase
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}*/
	
/*	public static void maimongoDatabasen(String[] args) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Student.class, StudentElastic.class).byDefault();
	    MapperFacade mapper = mapperFactory.getMapperFacade();
	    Student stud = new Student("a","10","cbe","ind");
	    StudentElastic studEs = mapper.map(stud, StudentElastic.class);
	    
	    studEs.getClass().getAnnotations();
	    System.out.println(stud.toString());
	    System.out.print		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);
ln(studEs.toString());
	}*/
	
/*    private static voi@ResponseBodyd addOneDocument(MongoCollection<Document> col) {
    	 
        // Sample document.
        Document emp1 = new Document();
        emp1.put("name", "yatin batra");
        emp1.put("age", "30");
        emp1.put("city", 		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);
"cbe");
        emp1.put("country", "india");
        col.insertOne(emp1);
    }*/
    
/*    public static void main(String[] args) {
    	int port_no = 27017;
        String host_name = "localhost", db_name = "bharath", db_coll_name = "student";
        // default factory implementation
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String,String> parameter = new HashMap<String, String>();

		// user credential
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "admin");

		// connection settings
		//parameter.put(SessionParameter.ATOMPUB_URL, "http://127.0.0.1/alfresco/api/-default-/public/cmis/versions/1.1/atom");
		parameter.put(SessionParameter.ATOMPUB_URL, "http://localhost:8080/alfresco/cmisatom");
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// create session
		//Session session = factory.getRepositories(parameter).get(0).createSession();
		List<Repository> repositories = factory.getRepositories(parameter);
		Session session = repositories.get(0).createSession();
        String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
        MongoClientURI uri = new MongoClientURI(client_url);
        
        // Connecting to the mongodb server using the given client uri.
        MongoClient mongo_client = new MongoClient(uri);
 
        // Fetching the database from the mongodb.
        MongoDatabase db = mongo_client.getDatabase(db_name);
 
        // Fetching the collection from the mongodb.
        MongoCollection<Document> coll = db.getCollection(db_coll_name);
        addOneDocument(coll);
 
	}*/
    
    /*public static void main(String[] args) {
    	System.out.println(mongoConfig.mongoDatabase());
//		System.out.println(mong);
	}
*/
/*	public static void main(String[] args){
@ResponseBody
		// default factory implementation
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String,String> parameter = new HashMap<String, String>();

		// user credential
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionPasaveFile/MONGOrameter.PASSWORD, "admin");

		// connection settings
		//parameter.put(SessionParameter.ATOMPUB_URL, "http://127.0.0.1/alfresco/api/-default-/public/cmis/versions/1.1/atom");
		parameter.put(SessionParameter.ATOMPUB_URL, "http://localhost:8080/alfresco/cmisatom");
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// create session
		//Session session = factory.getRepositories(parameter).get(0).createSession();
		List<Repository> repositories = factory.getRepositories(parameter);
		Session session = repositories.get(0).createSession();
		StudentController stud = new StudentController();
		stud.persistFileAlfresco();
	}*/

}
