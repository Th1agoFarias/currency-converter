package com.example.currencyconverter.ratescurrency;

public class Rates {

    public static class rates {
        private double usd;
        private double eur;
        private double gbp;
        private double ars;
        private double clp;

        public rates(double usd, double eur, double gbp, double ars, double clp) {
            this.usd = usd;
            this.eur = eur;
            this.gbp = gbp;
            this.ars = ars;
            this.clp = clp;
        }

        public double getUsd() {
            return usd;
        }

        public double getEur() {
            return eur;
        }

        public double getGbp() {
            return gbp;
        }

        public double getArs() {
            return ars;
        }

        public double getClp() {
            return clp;
        }

        @Override
        public String toString() {
            return "Rates{" +
                    "usd=" + usd +
                    ", eur=" + eur +
                    ", gbp=" + gbp +
                    ", ars=" + ars +
                    ", clp=" + clp +
                    '}';
        }
    }
}
