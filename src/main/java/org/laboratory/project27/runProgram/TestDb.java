package org.laboratory.project27.runProgram;

import org.laboratory.project27.repository.DriverRepository;

public class TestDb {
    public static void main(String[] args) {
        new DriverRepository().findAll();

    }
}
/*
 select fname,lname FROM actor
where actor_id not in
        (select actor_id from team
        where mainteam  = y);

select fname,lname FROM actor
where actor_id in(
select actor_id from team
where play_id in
(select play_Id from play
where author = "WilliamShakespeare"));

select Play.name AS Name,
AVG(Year(CURRENT DAY) - YEAR(ACTOR.Birthdate)) AS Age
FROM actor,team, play
WHERE Team.Play_Id = Play.Play_Id
AND  Team.Actor_Id = Actor.Actor_Id
group by Name
HAVING  AVG(Year(CURRENT DAY) - YEAR(ACTOR.Birthdate))
between 20 AND 30;

//SELECT Actor.Fname, Actor.Lname, COUNT(Team.Play_Id)
//FROM Actor
//JOIN Team
//ON Actor.Actor_Id = Team.Team_Id AND Role ="Y"
//(SELECT TEAM.Play_Id
//FROM Team
//JOIN Play
//ON   TEAM.Play_Id = Play.Play_id
//(SELECT((CURRDATE() -(PREMIERDATE())<=5) AS Period
//FROM PLay)
//GROUP BY COUNT(TEAM.Role)
//LIMIT(1)
//);





 */