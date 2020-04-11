import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TeamOwner extends Subscription{

    private LinkedList<Team> teams;
    private BudgetControl budgetControl;
    private LinkedList<Notification> notifications;
    private HashMap<Subscription, Team> mySubscriptions;

    //if already team owner of other teams
    public TeamOwner(Subscription sub, MainSystem ms, LinkedList<Team> teams) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.teams = teams;
        this.budgetControl = new BudgetControl();
        notifications = new LinkedList<>();
        mySubscriptions = new HashMap<>();
        //TODO add permissions
        //this.permissions.add();
    }

    //first time team owner
    public TeamOwner(Subscription sub, MainSystem ms, Team team) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.budgetControl = new BudgetControl();
        this.teams = new LinkedList<>();
        teams.add(team);
        team.addTeamOwner(this);
        notifications = new LinkedList<>();
        mySubscriptions = new HashMap<>();
        //TODO add permissions
        //this.permissions.add();
    }
    // adi
    public TeamOwner subscribeTeamOwner(Subscription sub, MainSystem ms, Team team){
        TeamOwner tO = new TeamOwner(sub, ms, team);
        mySubscriptions.put(tO, team);
        return tO;
    }
    // adi
    public boolean removeTeamOwner (TeamOwner tO, MainSystem ms, Team team){
        if (mySubscriptions.containsKey(tO)){
            mySubscriptions.remove(tO);
            for (Map.Entry<Subscription, Team> entry : tO.mySubscriptions.entrySet()) {
                if (entry.getValue().equals(team)){
                    tO.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                }
            }
            team.removeTeamOwner(tO);
            ms.removeUser(tO);
            Subscription newSub = new Subscription(ms, tO.getName(), tO.getPhoneNumber(), tO.getEmail(), tO.getUserName(), tO.getPassword());
            return true;
        }
        return false;
    }
    //<editor-fold desc="setters and getters">
    public void setTeam(Team team) {
        this.teams.add(team);
    }

    public void setBudgetControl(BudgetControl budgetControl) {
        this.budgetControl = budgetControl;
    }

    public LinkedList<Team> getTeams() {
        return teams;
    }

    public HashMap<Subscription, Team> getMySubscriptions() {
        return mySubscriptions;
    }

    public BudgetControl getBudgetControl() {
        return budgetControl;
    }

    //</editor-fold>
}
