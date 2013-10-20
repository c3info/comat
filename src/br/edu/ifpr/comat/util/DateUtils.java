package br.edu.ifpr.comat.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.text.ParseException;
import java.util.Date;

public class DateUtils {

    public static String formatarData(Date d) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return df.format(d);
    }

    public static Date dmaDate(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia);

        return c.getTime();
    }

    public static String date2Str(Date d) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return df.format(d);
    }

    /**
     * Converte os valores representando dia, mês e ano utilizando
     * date2Str(Date).
     *
     * @param ano Ano
     * @param mes Mês
     * @param dia Dia
     *
     * @return O dia, mês e ano formatados
     */
    public static String amd2DateStr(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();

        c.set(ano, mes, dia);

        return date2Str(c.getTime());
    }

    /**
     * Converte uma data caractere para sua representação Date.
     *
     * @param date_str Data caractere a ser convertida
     * @param style Estilo (formato) a ser utilizado na conversão. Deve-se usar
     * as constantes definidas na classe DateFormat
     *
     * @return A data convertida para Date
     */
    public static Date str2Date(String date_str, int style) {
        DateFormat df = DateFormat.getDateInstance(style);
        Date date = null;

        try {
            date = df.parse(date_str);
        } catch (ParseException ex) {
            date = null;
        }

        return date;
    }

    /**
     * Converte uma data caractere para Date utiliando str2Date(String, int).
     *
     * @param date Data caractere a ser convertida
     *
     * @return A data convertida
     */
    public static Date str2Date(String date) {
        return str2Date(date, DateFormat.MEDIUM);
    }

    /**
     * Converte dia, mês e ano em uma data Date.
     *
     * @param ano Ano
     * @param mes Mês
     * @param dia Dia
     *
     * @return Data convertida para Date
     */
    public static Date amd2Date(int ano, int mes, int dia) {
        return str2Date(amd2DateStr(ano, mes, dia));
    }
}
