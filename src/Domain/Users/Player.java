package Domain.Users;

import Domain.PageOwner;
import Domain.PrivatePage;
import Domain.LeagueManagment.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player implements PageOwner {
    private PrivatePage privatePage;
    private Team playerTeam;
    private String roleAtField;
    private TeamRole teamRole;
    private static final Logger LOG = LogManager.getLogger();

    public Player(TeamRole teamRole){
        this.privatePage = null;
        this.playerTeam = null;
        this.roleAtField = null;
        this.teamRole= teamRole;
    }

    public Player(TeamRole teamRole, Team playerTeam, String roleAtField) {
        this.privatePage = null;
        this.playerTeam = playerTeam;
        this.roleAtField = roleAtField;
        this.teamRole= teamRole;
    }

    //<editor-fold desc="Page Owner Functions">
    /**Or**/
    @Override
    public void addRecordToPage(String record) throws Exception {
        this.privatePage.addRecords(record);
    }
    /**Or**/
    @Override
    public void removeRecordFromPage(String record) throws Exception {
        this.privatePage.removeRecord(record);
    }
    /**Or**/
    @Override
    public boolean createPrivatePage() {
        PrivatePage p = new PrivatePage();
        if(this.privatePage==null){// you can have only one page
            this.privatePage=p;
            p.setPageOwner(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePrivatePage() {
        if(privatePage == null){
            return false;
        }
        this.privatePage.setPageOwner(null);
        this.privatePage=null;
        return true;
    }

    /**Or**/
    @Override
    public PrivatePage getPage() {
        return privatePage;
    }

    @Override
    public String getOwnerName() {
        return teamRole.getUserName();
    }

    //</editor-fold>



    //<editor-fold desc="getters and setters">
    public Team getTeam() {
        return playerTeam;
    }

    public PrivatePage getPrivatePage() {
        return privatePage;
    }

    public void setPrivatePage(PrivatePage privatePage) {
        this.privatePage = privatePage;
    }


    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }


    public String getRoleAtField() {
        return roleAtField;
    }

    public void setRoleAtField(String roleAtField) {
        this.roleAtField = roleAtField;
    }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }

    //</editor-fold>



}
