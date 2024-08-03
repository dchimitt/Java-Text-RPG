package actOneEnemies;

import com.dchimitt.main.Character;

import java.io.Serializable;

public class PlainsGoblin extends Character implements java.io.Serializable {

	public PlainsGoblin(String name, int currentAct) {
		super(name, 1, 1, 1, 10, 0, 2);
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
