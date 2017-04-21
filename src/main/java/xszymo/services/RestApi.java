package xszymo.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import xszymo.rest.objects.All.AllAllyChampionsTips;
import xszymo.rest.objects.All.AllChampions;
import xszymo.rest.objects.All.AllChampionsStats;
import xszymo.rest.objects.All.AllEnemyChampionsTips;
import xszymo.rest.objects.objects.ChampionAllyAdvice;
import xszymo.rest.objects.objects.ChampionEnemyAdvice;
import xszymo.rest.objects.objects.ChampionInfo;
import xszymo.rest.objects.objects.ChampionStats;

@RestController
public class RestApi {

	//use your key
	public static final String key = "";

	@Autowired
	public RestOperations operations;

	@RequestMapping("getChampion/{name}")
	public ChampionInfo getChampion(@PathVariable("name") String name) {
		Collection<ChampionInfo> champions = getChampionInfo();
		ChampionInfo champion = null;
		for (ChampionInfo x : champions)
			if (x.getName().equals(name))
				champion = x;

		if (champion == null)
			return null;
		return champion;
	}

	@RequestMapping("getChampionsInfo")
	public Collection<ChampionInfo> getChampionInfo() {
		String url = "https://global.api.riotgames.com/api/lol/static-data/EUW/v1.2/champion?champData=info&api_key="
				+ key;
		AllChampions api = operations.getForObject(url, AllChampions.class);
		Collection<ChampionInfo> champions = api.getData().values();
		return champions;
	}

	@RequestMapping("getChampionsStats")
	public Collection<ChampionStats> getChampionsStats() {
		String url = "https://euw1.api.riotgames.com/lol/static-data/v3/champions?champData=stats&api_key=" + key;
		AllChampionsStats api = operations.getForObject(url, AllChampionsStats.class);
		Collection<ChampionStats> champions = api.getData().values();
		return champions;
	}

	@RequestMapping("getEnemyChampionsTips")
	public Collection<ChampionEnemyAdvice> getEnemyChampionsTips() {
		String url = "https://euw1.api.riotgames.com/lol/static-data/v3/champions?champData=enemytips&api_key=" + key;
		AllEnemyChampionsTips api = operations.getForObject(url, AllEnemyChampionsTips.class);
		Collection<ChampionEnemyAdvice> champions = api.getData().values();
		return champions;
	}

	@RequestMapping("getAllyChampionsTips")
	public Collection<ChampionAllyAdvice> getAllyChampionsTips() {
		String url = "https://euw1.api.riotgames.com/lol/static-data/v3/champions?champData=allytips&api_key=" + key;
		AllAllyChampionsTips api = operations.getForObject(url, AllAllyChampionsTips.class);
		Collection<ChampionAllyAdvice> champions = api.getData().values();
		return champions;
	}
}
