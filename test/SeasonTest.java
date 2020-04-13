import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {

    /**Eden*/
    @Test
    void addLeagueWithTeams() throws Exception {
        League l=new League("hahal",null,null,null);
        Season s=new Season(null,1884);
        MainSystem sys=new MainSystem(null);
        HashSet<Team> teams1=new HashSet<Team>();
        HashSet<Player> players=new HashSet<>();
        Subscription sub=new Subscription(sys,"ttt","tt","tt","tt","ttt");
        for(int i=0;i<13 ;i++){
            players.add(new Player(sub,sys));

        }
        teams1.add(new Team("hahalufa",12,players,null,null));
        teams1.add(new Team("hapuel",12,players,null,null));
        teams1.add(new Team("macabi",12,players,null,null));
        Team teamFor2Tests =new Team("hapuel-Rishon",12,players,null,null);
        teams1.add(teamFor2Tests);
        s.addLeagueWithTeams(l,teams1);
        /**check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
        HashSet<Team> teams2=new HashSet<Team>();
        teams2.add(new Team("beitar",12,players,null,null));
        teams2.add(new Team("beitar2",12,players,null,null));
        teams2.add(teamFor2Tests);
        s.addLeagueWithTeams(l,teams2);
        /**add more teams and check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));

        /**double add again- both with no changes and equals*/
        s.addLeagueWithTeams(l,teams2);
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
        Assert.assertTrue(l.getTeamsInSeason().get(s).size()==6);

        /***check teams connection**/
            Iterator<Team> iter = teams2.iterator();
            while (iter.hasNext()) {
                Assert.assertTrue(((Team) iter.next()).getLeaguePerSeason().containsKey(s));
                Assert.assertTrue(((Team) iter.next()).getLeaguePerSeason().get(s).equals(l));
            }
    }
}