import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * Write a description of class Pidgeot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pidgeot extends Creature
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
        enemy = world.getPlayerOne();
        enemyType = enemy.getType();
        attackAnimation();
        
        if ( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            
            if( enemyType.equalsIgnoreCase ("Rock" ))
            {
                enemy.getHealthBar().add( -55/2 );
                getWorld().showText( "It's not very effective...",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                Greenfoot.delay(30);
            }
            else if ( enemyType.equalsIgnoreCase ("Grass" ))
            {
                enemy.getHealthBar().add( -55*2 );
                getWorld().showText( "It's super effective",getWorld().getWidth()/2,getWorld().getHeight() + 26 );
                Greenfoot.delay(30);
            }
            else 
            {
                enemy.getHealthBar().add( -55 );
            }
            
        }
        
        //Indentation issue and this should also be false
        world.setTurnNumber(true);
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
            setLocation( getX() - 1, getY() + 2 );
            
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
            switchCreature = world.getNewTwoCreature(0);
        }
        else
        {
            switchCreature = world.getNewTwoCreature(1);
        }
        
        if ( getHealthBar().getCurrent() <=0)
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature." );
        }
        else
        {
            
            while( getX() < getWorld().getWidth() - 1 )
            {
                setLocation( getX() + 5, getY());
                
                Greenfoot.delay(2);
            }
            
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            
            if ( idx == 0)
            {
                world.changePlayerTwo("Pikachu");
            }
            else
            {
                world.changePlayerTwo("Lapras");
            }
            
            switchCreature.switchedIn();
            world.setTurnNumber(true);
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
        
        while ( getX() > 325 )
        {
            setLocation( getX() - 5, getY());
            
            Greenfoot.delay(2);
        }
        
    }
    
    public Pidgeot(World w)
    {
        super(800,false,"Flying");
        
        getImage().scale(150,100);
        
        w.addObject( getHealthBar(),100,25);
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
            getWorld().showText("Pidgeot has fainted...",getWorld().getWidth()/2,getWorld().getHeight()/2 + 26);
            
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
