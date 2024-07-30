package actOneEnemies;

import com.dchimitt.main.Character;

public class CaveBossThraxx extends Character implements java.io.Serializable {

	public CaveBossThraxx(String name, int currentAct) {
		super(name, 3, 2, 1, 26, 0, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int attack() {
		int baseDamage = level * 2 + strength * 3;
	    int variability = (int) (Math.random() * (strength + dexterity));
	    return baseDamage + variability;
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
		int baseDefense = level * 5 + strength * 2;
	    int dexterityBonus = (int) (Math.random() * (dexterity + 1));
	    return baseDefense + dexterityBonus;
	}
}

