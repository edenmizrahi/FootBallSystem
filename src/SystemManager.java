import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SystemManager extends Subscription implements Observer {

    private HashSet<Complaint> complaints;//*
    private static final Logger LOG = LogManager.getLogger();

    public SystemManager(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.complaints = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public SystemManager (MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms,name,phoneNumber,email,userName,password);
        this.complaints = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public void buildRecomandationSystem(){

    }

    //<editor-fold desc="getters and setters">

    public HashSet<Complaint> getComplaints() {
        return complaints;
    }


    /**Eden*/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Complaint){

            if(arg instanceof Complaint){
                /**if it's not an answer notify*/
                if(((Complaint)arg).getAnswer()==null){
                    // TODO: 11/04/2020 Notifies about new Complaint
                }

            }
        }
    }

    /**
     * create new SystemManager , connect to him all the complaints
     * @param user
     * @codeBy Eden
     */
    public void addSystemManager(Subscription user){
        SystemManager newSystemManager=new SystemManager(user,system);
        newSystemManager.complaints=this.complaints;
    }
    /**Eden*/
    public void answerToComplaint (Complaint com, String ans){
        if(complaints.contains(com)){
            com.setAnswer(ans);
            com.send();
        }
    }
    /**Eden*/
    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        complaint.addObserver(this);
    }

    /**
     * Show details about the system events.
     * @return FileReader to the Log file
     * @throws FileNotFoundException
     * @codeby Eden
     */
    public FileReader showSystemInfo() throws FileNotFoundException {
        File file =new File("LOG.text");
        return new FileReader(file);
    }

    /**
     * remove user if it a valid system operation.
     * @param userToDelete
     * @return list of removed object
     * @throws Exception if the remove isnt valid
     * @codeBy Eden
     */
    public List<Object> removeUser(Subscription userToDelete) throws Exception {
        List<Object> objectsDeleted=new LinkedList<>();


        if(userToDelete instanceof Rfa) {
            /***must have at least one RFA at system**/
            if(system.numOfRfa()==1){
                throw new Exception("There is only one RFA left, you cannot delete it ");
            }
            objectsDeleted=deleteRfa(((Rfa)userToDelete));
        }
        if(userToDelete instanceof SystemManager) {
            if(userToDelete==this){
                throw  new Exception("You Cannot Delete Yourself!!!");
            }
            /***must have at least one system Manager at system**/
            if(system.getSystemManagers().size()==1){
                throw new Exception("There is only one System Manager left, you cannot delete it ");
            }
            objectsDeleted=deleteSystemManager(((SystemManager)userToDelete));
        }
        if(userToDelete instanceof Referee) {
            objectsDeleted=deleteReferee(((Referee)userToDelete));
        }
        if(userToDelete instanceof Coach) {
            /***canot remove coach that already connect to team**/

            if(((Coach)userToDelete).getCoachTeam()!=null){
                throw new Exception("you have to subscribe another Coach to "+((Coach)userToDelete).getCoachTeam().getName()+" Team first");
            }
            objectsDeleted=deleteCoach(((Coach)userToDelete));
        }
        if(userToDelete instanceof Player) {
            /**can remove player only if he didnt have any team or
             * at team has at least 11 player***/
            if(((Player)userToDelete).getTeam()!=null) {
                if (((Player) userToDelete).getTeam().getPlayers().size() <= 11) {
                    throw new Exception("You Cannot Delete Player From " + ((Player) userToDelete).getTeam().getPlayers() + " Team , at team have to be at least 11 Players!");
                }
            }
            objectsDeleted=deletePlayer(((Player)userToDelete));
        }

        if(userToDelete instanceof TeamOwner) {
            objectsDeleted=deleteTeamOwner(((TeamOwner)userToDelete));
        }
        if(userToDelete instanceof TeamManager) {
            objectsDeleted=deleteTeamManager(((TeamManager)userToDelete));
        }

        return objectsDeleted;
    }

    /**
     * delete Team Manager and delete all his subscribe .
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteTeamManager(TeamManager userToDelete) {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        if(userToDelete.getTeam()!=null) {
            userToDelete.getTeam().setTeamManager(null);
        }
        system.removeUser(userToDelete);
        return res;

        // TODO: 15/04/2020  take from adi the function that remeove the subscribe.
    }

    /**
     * delete Team Owner only if he isn't a Founder of any team and delete all his subscribe .
     * @param userToDelete
     * @throws Exception if team owner is founder of any team.
     * @codeBy Eden
     */
    private List<Object> deleteTeamOwner(TeamOwner userToDelete) throws Exception {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        userToDelete.getBudgetControl().removeTeamOwner(userToDelete);
        userToDelete.getSystem().removeUser(userToDelete);
        /***remove all the subscription**/
        LinkedList<Team> OwnerTeams =userToDelete.getTeams();
        for(Team t: OwnerTeams){
            if(t.getFounder()==userToDelete){
                throw new Exception(userToDelete.getName()+" is founder if:"+ " "+t.getName() +"please change the fonder");
            }
        }

        HashMap<Subscription, Team> subscriptions= userToDelete.getMySubscriptions();
        for(Map.Entry<Subscription,Team> sub: subscriptions.entrySet()){
            userToDelete.removeTeamOwner(((TeamOwner)sub.getKey()),system,sub.getValue());
        }
        return res;
    }
    /**
     * delete Player only if the team has at least 11 other players
     * @param userToDelete
     * @throws Exception
     * @codeBy Eden
     */
    private List<Object> deletePlayer(Player userToDelete) throws Exception {
        if( userToDelete.getTeam()!=null) {
            userToDelete.getTeam().removePlayer(userToDelete);
        }
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    /**
     * delete Coach .
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteCoach(Coach userToDelete) {
        system.removeUser(userToDelete);
        List<Object> re=new LinkedList<>();
        re.add(userToDelete);
        return  re;
    }

    /**
     * delete Referee only if he hasn't any future match .
     * @param userToDelete
     * @return list with the referee object.
     * @throws Exception if referee has future matches.
     * @codeBy Eden
     */
    private List<Object> deleteReferee(Referee userToDelete) throws Exception {
        //check the all matches that the referee is refereeing
        for (Match m : userToDelete.getMatches()) {
            if (m.getDate().after(new Date(System.currentTimeMillis()))) {
                throw new Exception("You cannot remove referee , he has matches in this season");
            }
        }
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    /**
     * delete System Manager.
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteSystemManager(SystemManager userToDelete) {
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }
    /**
     * delete RFA , disconnect from budget control and from system
     * @param userToDelete
     * @codeBy Eden
     */
    private List<Object> deleteRfa(Rfa userToDelete){
        userToDelete.getBudgetControl().removeRfa(userToDelete);
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }
}

