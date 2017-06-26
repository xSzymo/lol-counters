package xszymo.controllers.others;

import xszymo.rest.objects.ChampionInfo;
import xszymo.services.RestApiStatic;

import java.util.Collection;

public class Algorithm {
	public static final String SUPP = "support";
	public static final String ADC = "adc";
	public static final String MID = "mid";
	public static final String TOP = "top";
	public static final String JUNGLE = "jungle";

	public static Collection<ChampionInfo> algorithm(Collection<ChampionInfo> ally, Collection<ChampionInfo> enemy,
	                                                 ChampionInfo you, String line) {
		Collection<ChampionInfo> allChampions = RestApiStatic.getAllChampions();
		if (line.equals("Mid"))
			return Other2.sortChampions(Other.getCountersForChampionWithOutLine(allChampions, ally, enemy, you, line), line);
		else
			return Other.getCountersForChampionWithOutLine(allChampions, ally, enemy, you, line);
	}
}
