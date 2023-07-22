package com.example.currencyconverter;

import com.example.currencyconverter.ConnectionApi.ApiService;
import com.example.currencyconverter.ratescurrency.Rates;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;

public class CurrencyConverterController {
    @FXML
    private TextField txtValueReal;

    @FXML
    private ComboBox<String> cmbCurrency;

    @FXML
    private Label lblResult;

    private final ApiService apiService = new ApiService();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @FXML
    protected void convertCurrency() {
        try {
            double valueReal = Double.parseDouble(txtValueReal.getText());
            String currency = cmbCurrency.getValue();

            Rates.rates rates = apiService.getExchangeRates();

            if (rates != null) {
                double convertedValue = 0.0;

                switch (currency) {
                    case "US Dollar":
                        convertedValue = valueReal / rates.getUsd();
                        lblResult.setText("Conversion from Brazilian Real to US Dollar: " + formatDecimal(convertedValue));
                        break;
                    case "Euro":
                        convertedValue = valueReal / rates.getEur();
                        lblResult.setText("Conversion from Brazilian Real to Euro: " + formatDecimal(convertedValue));
                        break;
                    case "British Pound":
                        convertedValue = valueReal / rates.getGbp();
                        lblResult.setText("Conversion from Brazilian Real to British Pound: " + formatDecimal(convertedValue));
                        break;
                    case "Argentine Peso":
                        convertedValue = valueReal / rates.getArs();
                        lblResult.setText("Conversion from Brazilian Real to Argentine Peso: " + formatDecimal(convertedValue));
                        break;
                    case "Chilean Peso":
                        convertedValue = valueReal / rates.getClp();
                        lblResult.setText("Conversion from Brazilian Real to Chilean Peso: " + formatDecimal(convertedValue));
                        break;
                    default:
                        lblResult.setText("Invalid option.");
                        return;
                }
            } else {
                lblResult.setText("Unable to fetch exchange rates.");
            }
        } catch (NumberFormatException e) {
            lblResult.setText("Enter a valid value for conversion.");
        } catch (IOException | InterruptedException e) {
            lblResult.setText("An error occurred while fetching exchange rates: " + e.getMessage());
        }
    }

    private String formatDecimal(double value) {
        return decimalFormat.format(value);
    }
}