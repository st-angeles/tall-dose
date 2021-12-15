package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {
    private Passenger doris;
    private Passenger angelina;

    @BeforeEach
    void setupPassengers(){
        doris = new Passenger("Doris", false);
        angelina = new Passenger("Angelina", true);
    }

    @Nested
    @DisplayName("Given there is a premium flight")
    class PremiumFlightTest{
        private Flight premiumFlight;

        @BeforeEach
        void setupFlight(){
            premiumFlight = new PremiumFlight("4");
        }

        @Nested
        @DisplayName("When there is a non-VIP passenger")
        class NonVip{

            @Test
            @DisplayName("Then it can't be added, nor removed from a premium fligh")
            public void testPremiumFlightNonVip(){
                assertAll("Verify all conditions for a non-VIP passenger and a premium flight",
                        () -> assertEquals("4", premiumFlight.getId()),

                        () -> assertEquals(false,premiumFlight.addPassenger(doris)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(doris)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                        );
            }
        }

        @Nested
        @DisplayName("When there is a VIP passenger")
        class Vip{

            @Test
            @DisplayName("Then it can be added and removed from a premium flight")
            public void testPremiumFlightVip(){

                        assertAll("Verify all conditions for a VIP passenger and a premium flight",
                        () -> assertEquals("4", premiumFlight.getId()),

                        () -> assertEquals(true, premiumFlight.addPassenger(angelina)),
                        () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                        () -> assertEquals(true,premiumFlight.getPassengersSet().contains(angelina)),
                        () -> assertEquals(true, premiumFlight.removePassenger(angelina)),
                                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                        );
            }

            @RepeatedTest(5)
            @DisplayName("Then it can not be added to a premium flight more than once")
            public void testPremiumFlightVipAddedOnlyOnce(RepetitionInfo repetitionInfo){
                for(int i: new int[repetitionInfo.getTotalRepetitions()]){
                    premiumFlight.addPassenger(angelina);
                }
                assertAll("Verify all conditions for adding a VIP to a premium flight only once",
                        () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                        () -> assertTrue(premiumFlight.getPassengersSet().contains(angelina)),
                        ()->assertEquals("Angelina",premiumFlight.getPassengersSet().iterator().next().getName())
                        );
            }
        }
    }

    @Nested
    @DisplayName("Given there is a business flight")
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setupFlight(){
            businessFlight = new BusinessFlight("2");
        }

        @Nested
        @DisplayName("When there is a non-VIP passenger")
        class NonVip {

            @DisplayName("Then it can't be added nor removed from a business flight")
            @Test
            public void testBusinessFlightNonVip() {
          assertAll("Verify all conditions for a non-VIP passenger and a business flight",

                  () -> assertEquals("2", businessFlight.getId()),

                  () -> assertEquals(false, businessFlight.addPassenger(doris)),
                  () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                  () -> assertEquals(false, businessFlight.removePassenger(doris)),
                  () -> assertEquals(0, businessFlight.getPassengersSet().size()) );
            }
        }

        @Nested
        @DisplayName("When there is a VIP passenger")
        class Vip {

            @DisplayName("Then it can be added to, but it can't be removed from a business flight")
            @Test
            public void testBusinessFlightVip() {
                assertAll("Verify all conditions for a VIP passenger and a business flight",

                        ()->assertEquals("2", businessFlight.getId()),

                        ()->assertEquals(true, businessFlight.addPassenger(angelina)),
                        ()->assertEquals(1, businessFlight.getPassengersSet().size()),
                        ()->assertEquals(false, businessFlight.removePassenger(angelina)),
                        ()->assertEquals(1, businessFlight.getPassengersSet().size()),
                        ()->assertEquals(true, businessFlight.getPassengersSet().contains(angelina)) );
            }

            @RepeatedTest(5)
            @DisplayName("Then it can't be added to a business flight more than once")
            public void testBusinessFlightVipAddedOnlyOnce(RepetitionInfo repetitionInfo){
                for(int i: new int[repetitionInfo.getTotalRepetitions()]){
                        businessFlight.addPassenger(angelina);
                }
                assertAll("Verify all conditions for adding a VIP passenger only once to a business flight",
                        ()->assertEquals(1, businessFlight.getPassengersSet().size()),
                        ()->assertEquals(true, businessFlight.getPassengersSet().contains(angelina)),
                        ()->assertEquals("Angelina",businessFlight.getPassengersSet().iterator().next().getName())
                        );
            }
        }
    }

    @Nested
    @DisplayName("Given there is an economy flight")
    class EconomyFlightTest {

        private Flight economyFlight;

        @BeforeEach
        void setupFlight(){
            economyFlight = new EconomyFlight("1");
        }

        @Nested
        @DisplayName("When we have a non-vip passenger")
        class NonVip {
            @Test
            @DisplayName("Then it can be added to and removed from an economy flight")
            public void testEconomyFlightNonVip() {
                assertAll("Verify all conditions for a usual passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(doris)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertEquals(true, economyFlight.getPassengersSet().contains(doris)),
                        () -> assertEquals(true, economyFlight.removePassenger(doris)),
                        () -> assertEquals(0, economyFlight.getPassengersSet().size())
                );
            }

            @RepeatedTest(5)
            @DisplayName("Then it can't be added to an economy flight more than once")
            public void testEconomyFlightNonVipAddedOnlyOnce(RepetitionInfo repetitionInfo){
                for(int i : new int[repetitionInfo.getTotalRepetitions()]){
                    economyFlight.addPassenger(doris);
                }
                assertAll("Verify that a non-VIP passenger can be added to an economy flight only once",
                        ()->assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(doris)),
                        () -> assertEquals("Doris",economyFlight.getPassengersSet().iterator().next().getName())
                        );
            }
        }

        @Nested
        @DisplayName("When there is VIP passenger")
        class Vip {
            @Test
            @DisplayName("Then it can be added to and it can't be removed from an economy flight")
            public void testEconomyFlightVip() {
                assertAll("Verify all conditions for a VIP passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),

                        () -> assertEquals(true, economyFlight.addPassenger(angelina)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertEquals(false, economyFlight.removePassenger(angelina)),
                        ()-> assertEquals(1, economyFlight.getPassengersSet().size()),

                        ()-> assertEquals(true, economyFlight.getPassengersSet().contains(angelina))
                );
            }
        }
    }
}
