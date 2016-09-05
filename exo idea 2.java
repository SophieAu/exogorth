// Lambda Listener
testButton.addActionListener(e -> System.out.println("Click Detected by Lambda Listener"));



just generally, use all the stuff from that one blog post:
Why Java? Tales from a Python Convert : sookocheff.com

make everything as private as possible

don't use getters and setters unless absolutely neccessary (currently only on the ImageLoader and then in the GameCharacter methods (collisionBox and damage). really need to get rid of them

make sure everything is as decoupled as possible (only use static (final) stuff in other classes)

comments on every method and class, on public methods and classes use javadoc, on private classes use pseudo java-doc
use the formuliereung of uncle bob (TO do the goal of the method, the method does this (only go one step back))

add threads for drawing (maybe update as well)



Add an EnemyController class (and maybe even a BulletController although I'm not too sure about that).

enemy.collides(enemy2)
enemy.evade
enemy2.evade

enemy.collides(wall)
enemy.evade
