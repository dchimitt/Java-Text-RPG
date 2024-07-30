package actOneEnemies;

// enemies created in order of areas traversed in game
public class ActOneEnemyCreation {
	
	// Reizart plains monsters
	public PlainsGoblin createPlainsGoblin() {
		return new PlainsGoblin("Plains Goblin", 1);
	}
	public PlainsSnake createPlainsSnake() {
		return new PlainsSnake("Plains Snake", 1);
	}
	
	// Reizart cave monsters
	public CaveBat createCaveBat() {
		return new CaveBat("Cave Bat", 2);
	}
	public CaveSpider createCaveSpider() {
		return new CaveSpider("Cave Spider", 2);
	}
	public CaveSlug createCaveSlug() {
		return new CaveSlug("Cave Slug", 2);
	}
	
	// Reizart cave boss
	public CaveBossThraxx createCaveBossThraxx() {
		return new CaveBossThraxx("Thraxx the Stonecrusher", 3);
	}
}
