import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Fan extends User implements Observer {

    private List<PrivatePage> myPages;
    private List<Complaint> myComplaints;
    private LinkedList <Match> matchesFollow;
    private LinkedList<String> searchHisroty;
    private static final Logger LOG = LogManager.getLogger();

    //from subscription:
    private String name;
    private String phoneNumber;
    private String email;
    private String userName;
    private String password;

    public Fan(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
        myPages = new LinkedList<>();
        myComplaints = new LinkedList<>();
        matchesFollow=new LinkedList<>();
        this.searchHisroty= new LinkedList<>();
    }

    //<editor-fold desc="getters and setters">

    public List<PrivatePage> getMyPages() {
        return myPages;
    }

    public void setMyPages(List<PrivatePage> myPages) {
        this.myPages = myPages;
    }

    public List<Complaint> getMyComplaints() {
        return myComplaints;
    }

    public void setMyComplaints(List<Complaint> myComplaints) {
        this.myComplaints = myComplaints;
    }

    public LinkedList<Match> getMatchesFollow() {
        return matchesFollow;
    }

    public void setMatchesFollow(LinkedList<Match> matchesFollow) {
        this.matchesFollow = matchesFollow;
    }

    public void setSearchHisroty(LinkedList<String> searchHisroty) {
        this.searchHisroty = searchHisroty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //</editor-fold>


    /**Eden*/
    public void  addMatchFollow(Match m){
        matchesFollow.add(m);
        m.addObserver(this);
    }

    /**Eden*/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Match){
            /**change place or time event */
            /**Game result*/
            if(arg instanceof String){
                /**result event**/
                if(((String)arg).contains("Result") ){

                }
                else{
                    /**change place event */
                    if(((String)arg).contains("Place") ){

                    }
                    else{
                        /**change time event */
                        if(((String)arg).contains("Time") ){

                        }
                    }
                }
            }
            else{
                if(arg instanceof Goal){
                    return;
                }
                if(arg instanceof RedCard){
                    return;
                }
                if(arg instanceof YellowCard){
                    return;
                }
                if(arg instanceof OffSide){
                    return;
                }
                if(arg instanceof Offense){
                    return;
                }
                if(arg instanceof Injury){
                    return;
                }
                if(arg instanceof Replacement){
                    return;
                }
            }
        }
        if(o instanceof Complaint){
            Complaint comp=((Complaint)o);
            String ans=comp.getAnswer();
            // TODO: 11/04/2020 do something with ans..
        }
    }

    /**Eden*/
    public void addNewComplaint(String comp){
        Complaint complaint=new Complaint(this,system);
        myComplaints.add(complaint);
        complaint.setContent(comp);
        complaint.send();

        complaint.addObserver(this);

    }

    /**Eden*/
    public void addToSearchHistory(String search){
        searchHisroty.add(search);
    }

    /**Eden*/
    public LinkedList<String> getSearchHisroty() {
        return searchHisroty;
    }


    /**Or**/
    public void subToPage(PrivatePage privatePage) throws Exception {
        if(privatePage== null){
            throw new Exception("private page null");
        }
        myPages.add(privatePage);
        privatePage.addFan(this);
        LOG.info(String.format("%s - %s", getUserName(), "subscriped to page"));
    }


    public void addComplaint(){

    }

    /**OR**/
    public void addPage(PrivatePage page){
        myPages.add(page);
    }

    /**OR**/
    public void logOut(){
        //nothing to do now.....
        LOG.info(String.format("%s - %s", userName, "loged out from system"));
    }
}
