then add to update render: if (enemy.startPosition-movedDistance <= 800) { do everything }

should take he load off a bit


kinda do it like this
ArrayList<> enemiesBacklog; //fomer existingEnemies
ArrayList<> visibleEnemies


update(){
for(int i = 0; i<= existingEnemies.size(); i++){
     tempEnemy = enemiesBacklog.get(i);
     if(tempEnemy.xPosition > Level.progress+800)
          break;
     tempEnemy.xPosition -= Levelprogress;
     visibleEnemies.add(tempEnemy);
}

for(int i = 0; i<= visibleEnemies.size(); i++){
     tempEnemy = visibleEnemies.get(i);
     if(tempEnemy.xPosition<=800)
          tempEnemy.update();
}
}


In Level constructor call
     //created all enemies
     Collections.sort(enemiesAndBullets.enemiesBacklog);
}

in Enemy:
implements Comparable{
public int compareTo(Enemy enemy){
    return(xPosition - enemy.xPosition);
}
