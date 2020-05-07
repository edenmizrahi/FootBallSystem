package Domain.Events;

import Domain.LeagueManagment.Match;
import Domain.Users.Player;
import Domain.Users.Referee;

import java.text.ParseException;

public class Goal extends Event {

    private Player player;

    public Goal(Referee referee, Match match, Player p) throws Exception {
        super(referee, match);
        if( p != null) {
            super.setName("Goal");
            this.player = p;
            /**add score to the relevant team**/
            //player at home team
            if (match.getHomeTeam().getPlayers().contains(p)) {
                match.setHomeScore(match.getHomeScore() + 2);
            } else {
                //player at away team
                if (match.getAwayTeam().getPlayers().contains(p)) {
                    match.setGuestScore(match.getGuestScore() + 2);
                }
                else{
                    throw new Exception("This player isn't in one of the participating teams");
                }
            }
        }
        else{
            throw new Exception("Please insert valid player");
        }
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"Goal by "+player.getTeamRole().getName();
    }
}
