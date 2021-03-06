import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * Write a description of class Ivysaur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ivysaur extends Creature
{
    private Creature enemy;
    private String enemyType;
    
    /**
     * attack sets how much the enemy's healthbar will deplete
     * 
     * @param idx list all of the creatures including types
     * @return there is no return
     */
    public void attack( int idx)
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        enemy = world.getPlayerTwo();
        enemyType = enemy.getType();
        attackAnimation();
        
        if ( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            
            if( enemyType.equalsIgnoreCase ( "Electric" ))
            {
                enemy.getHealthBar().add( -60/2 );
                getWorld().showText( "It's not very effective...",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                Greenfoot.delay(30);
            }
            else if ( enemyType.equalsIgnoreCase ("Flying" ))
            {
                enemy.getHealthBar().add( -60/2 );
                getWorld().showText( "It's not very effective...",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                Greenfoot.delay(30);
            }
            else if ( enemyType.equalsIgnoreCase ("Water" ))
            {
                enemy.getHealthBar().add( -60*2 );
                getWorld().showText( "It's super effective",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                Greenfoot.delay(30);
            }
            else 
            {
                enemy.getHealthBar().add( -60 );
            }
            
        }
        
        //Indentation issue and this should also be false
        world.setTurnNumber(false);
    }
    
     /**
     * attackAnimation show the creature's attack animation
     * 
     * @param there are no parameters
     * @return there is no return
     */
    private void attackAnimation()
    {
        int originalX = getX();
        int originalY = getY();
        
        for ( int i = 0; i < 15 ;i++)
        {
            setLocation( getX() + 1, getY() - 2 );
            
            Greenfoot.delay(10);
        }
        
        setLocation(originalX,originalY);
    }
    
    /**
     * switchCreature sets the new creature
     * 
     * @param idx list all of the creatures
     * @return there is no return
     */
    public void switchCreature( int idx )
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        Creature switchCreature;
        
        if ( idx == 0)
        {
            switchCreature = world.getNewOneCreature(0);
        }
        else
        {
            switchCreature = world.getNewOneCreature(1);
        }
        
        if ( getHealthBar().getCurrent() <=0)
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature." );
        }
        else
        {
            
            while( getX() > 0 )
            {
                setLocation( getX() - 5, getY());
                
                Greenfoot.delay(2);
            }
            
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            
            if ( idx == 0)
            {
                world.changePlayerOne("Charmander");
            }
            else
            {
                world.changePlayerOne("Golem");
            }
            
            switchCreature.switchedIn();
            world.setTurnNumber(false);
        }
        
    }
    
     /**
     * switchCreature switches this creature to a new creature
     * 
     * @param there are no parameters
     * @return there is no return
     */
    public void switchedIn()
    {
        getImage().setTransparency(225);
        getHealthBar().getImage().setTransparency(255);
        
        while ( getX() < 75)
        {
            setLocation( getX() + 5, getY());
            
            Greenfoot.delay(2);
        }
        
    }
    
    public Ivysaur(World w)
    {
        super(700,true,"Grass");
        
        getImage().scale(150,100);
        
        w.addObject( getHealthBar(),300,w.getHeight()-50);
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the Charmander wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        CreatureWorld playerWorld=(CreatureWorld)getWorld();
        
        if(getHealthBar().getCurrent() <=0)
        {
            getWorld().showText("Ivysaur has fainted...",getWorld().getWidth()/2,getWorld().getHeight()/2 + 26);
            
            if ( playerWorld.getNewTwoCreature(0).getHealthBar().getCurrent() > 0 )
            {
                switchCreature(0);
                playerWorld.setTurnNumber(true);
                getWorld().showText( "",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                getWorld().removeObject(this);
            }
            else if ( playerWorld.getNewTwoCreature(1).getHealthBar().getCurrent() > 0 )
            {
                switchCreature(1);
                playerWorld.setTurnNumber(true);
                getWorld().showText( "",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                getWorld().removeObject(this);
            }
        
        }
        
        Greenfoot.delay(30);
    }    
}
