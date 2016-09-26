package ssa;

public class Mainline {

	public static void main(String[] args) {
		
		HibernateConnector hc = new HibernateConnector();

		//insert several majors
		hc.insertMajor(new Major("Basket Weaving",400));
		hc.insertMajor(new Major("American Ninja Chess",1200));
		hc.insertMajor(new Major("Renaissance Art of the Circle",800));
	    hc.insertMajor(new Major("Don't Delete Me",1600));
	    
	    //update majors
		hc.updateMajor(hc.getId("Basket Weaving"),"Advanced Basket Weaving");
		hc.updateMajor(hc.getId("American Ninja Chess"), 1325);
		hc.updateMajor(hc.getId("Don't Delete Me"), "Really Don't Delete Me!", 1500);
		
		//delete a major
		hc.deleteMajor(hc.getId("Really Don't Delete Me!"));
		
		//print out all majors on record in the database
		System.out.println(hc);
		
		hc.close();
	}

}
