package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void testMain() {
		GildedRose.main(null);
	}
	
	@Test
	public void testDecreaseOfQualityAndSellIn() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.setItem(new Item("Elixir of the Mongoose", 5, 7));
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality0 = items.get(0).getQuality();
		int quality1 = items.get(1).getQuality();
		int quality2 = items.get(2).getQuality();
		
		int sellIn0 = items.get(0).getSellIn();
		int sellIn1 = items.get(1).getSellIn();
		int sellIn2 = items.get(2).getSellIn();
		
		assertEquals("Failed quality for Dexterity Vest", 19, quality0);
		assertEquals("Failed quality for Elixir of the Mongoose", 6, quality1);
		assertEquals("Failed quality for Conjured Mana Cake", 5, quality2);
		
		assertEquals("Failed sell in for Dexterity Vest", 9, sellIn0);
		assertEquals("Failed sell in for Elixir of the Mongoose", 4, sellIn1);
		assertEquals("Failed sell in for Conjured Mana Cake", 2, sellIn2);
		
	}
	
	@Test
	public void testPassedSellByDate() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 0, 20));
		inn.setItem(new Item("+5 Dexterity Vest", -5, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality0 = items.get(0).getQuality();
		int sellIn0 = items.get(0).getSellIn();
		
		int quality1 = items.get(1).getQuality();
		int sellIn1 = items.get(1).getSellIn();
		
		assertEquals("Failed quality for Dexterity Vest", 18, quality0);
		assertEquals("Failed sell in for Dexterity Vest", -1, sellIn0);
		
		assertEquals("Failed quality for Dexterity Vest", 0, quality1);
		assertEquals("Failed sell in for Dexterity Vest", -6, sellIn1);

	}
	
	@Test
	public void testNegativeQuality() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 5, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Dexterity Vest. Quality is never negative.", 0, quality);

	}
	
	@Test
	public void testAgedBrie() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 10));
		inn.setItem(new Item("Aged Brie", 0, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality0 = items.get(0).getQuality();
		int quality1 = items.get(1).getQuality();
		
		int sellIn0 = items.get(0).getSellIn();
		int sellIn1 = items.get(1).getSellIn();
		
		assertEquals("Failed quality for Aged Brie.", 11, quality0);
		assertEquals("Failed quality for Aged Brie.", 12, quality1);
		
		assertEquals("Failed sell in for Aged Brie.", 9, sellIn0);
		assertEquals("Failed sell in for Aged Brie.", -1, sellIn1);

	}
	
	@Test
	public void testMaximumQuality() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 50));
		inn.setItem(new Item("Aged Brie", 0, 50));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality0 = items.get(0).getQuality();
		int quality1 = items.get(1).getQuality();
		
		assertEquals("Failed quality for Aged Brie.", 50, quality0);
		assertEquals("Failed quality for Aged Brie.", 50, quality1);

	}
	
	@Test
	public void testSulfuras() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -5, 80));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality0 = items.get(0).getQuality();
		int quality1 = items.get(1).getQuality();
		int sellIn0 = items.get(0).getSellIn();
		int sellIn1 = items.get(1).getSellIn();
		
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros.", 80, quality0);
		assertEquals("Failed sell in for Sulfuras, Hand of Ragnaros.", 10, sellIn0);
		
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros.", 80, quality1);
		assertEquals("Failed sell in for Sulfuras, Hand of Ragnaros.", -5, sellIn1);

	}
	
	@Test
	public void testBackStagePasses() {

		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		int quality0 = items.get(0).getQuality();
		int quality1 = items.get(1).getQuality();
		int quality2 = items.get(2).getQuality();
		int quality3 = items.get(3).getQuality();
		int quality4 = items.get(4).getQuality();

		
		assertEquals("Failed quality Backstage passes.", 11, quality0);
		assertEquals("Failed quality Backstage passes.", 12, quality1);
		assertEquals("Failed quality Backstage passes.", 13, quality2);
		assertEquals("Failed quality Backstage passes.", 0, quality3);
		assertEquals("Failed quality Backstage passes.", 50, quality4);

	}
	
	// LOOP TESTING
	
	@Test
	public void testSkipLoop() {

		GildedRose inn = new GildedRose();
		inn.oneDay();

	}
	
}
