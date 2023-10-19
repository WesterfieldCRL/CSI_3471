package edu.baylor.cs.junit.demo.app;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.baylor.cs.junit.demo.app.impl.U2MusicBoxRejectingCents;
import edu.baylor.cs.junit.demo.app.objects.Coin;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class U2BoxTester extends BoxTester {
	
    @BeforeEach
    void init() {
    	box = new U2MusicBoxRejectingCents();
    }

    /**
     * We are overriding the inherited one as we do not accept cents
     */
    @Test
    void feed() {
    	box.insertCoin(Coin.dollar);
    	assertEquals(1.0f, box.balance(), "Cent expected");
    
    }

	@SuppressWarnings("unused")
	private static Stream<Arguments> coins() {
		return Stream.of(
				Arguments.of(new Coin[]{Coin.dollar,Coin.dime,Coin.cent}, 1.00f),
				Arguments.of(new Coin[]{Coin.dollar,Coin.nicle,Coin.cent,Coin.quarter}, 1.0f),
				Arguments.of(new Coin[]{Coin.dollar,Coin.dime, Coin.nicle,Coin.cent,Coin.quarter}, 1.00f)
		);
	}

    @Test
    void feedWithCentIgnores() {
    	box.insertCoin(Coin.dollar);
    	box.insertCoin(Coin.cent);
    	box.insertCoin(Coin.dime);
    	box.insertCoin(Coin.nicle);
    	box.insertCoin(Coin.quarter);
    	assertEquals(1f, box.balance(), "Cent expected");
    	
    }

	@Test
		//@Disabled("TODO: need to fix :)... yes you!")
	void correctDeduction() {
		box.insertCoin(Coin.cent);
		box.insertCoin(Coin.dollar);
		box.playSong(1);
		assertEquals(.00f,box.balance());
	}
    

}