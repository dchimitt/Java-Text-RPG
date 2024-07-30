package actOneEnemies;

import com.dchimitt.main.Character;
import com.dchimitt.main.Room;

public class ReizartBat extends Character implements java.io.Serializable {

	public ReizartBat(String name, int strength, int dexterity, int intelligence, int maximumHp, int maximumMana,
			int level, Room currentRoomInAct) {
		super(name, strength, dexterity, intelligence, maximumHp, maximumMana, level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int attack() {
		return (int) (Math.random() * (level / 2 + strength * 2) + dexterity * 2);
	}

	@Override
	public int useAbility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int useMagic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int defend() {
		return (int) (Math.random() * (level / 2 + strength * 2) + dexterity * 2);
	}

}
