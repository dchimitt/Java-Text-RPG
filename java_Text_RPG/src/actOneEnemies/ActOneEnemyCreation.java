package actOneEnemies;

public class ActOneEnemyCreation {
	public CaveBat createCaveBat() {
		return new CaveBat("Cave Bat", 2);
	}
	
	public CaveSpider createCaveSpider() {
		return new CaveSpider("Cave Spider", 2);
	}
	
	public CaveSlug createCaveSlug() {
		return new CaveSlug("Cave Slug", 2);
	}
}
