package com.br.ifpr.coffeenator.myclasses;

import android.content.Context;
import android.icu.lang.UScript;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;

import com.br.ifpr.coffeenator.activitys.HistoricoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBConector {
    public static String DB_URL = "https://coffeenator.000webhostapp.com/";

    private DBConector () {}

    public static JSONArray readJSONFromURL(String url_str, Context context) throws JSONException, IOException {
        if(!checkNetworkConnection(context)){
            return null;
        }
        checkThreadPolicy();
        URL url = new URL(url_str);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String json;
        while((json = bufferedReader.readLine()) != null){
            sb.append(json + "\n");
        }
        JSONArray jsonArray = new JSONArray(sb.toString().trim());
        return  jsonArray;
    }

    public static boolean insertIntoURL(String url_str, Context context) throws IOException{
        if(!checkNetworkConnection(context)){
            return false;
        }
        checkThreadPolicy();
        URL url = new URL(url_str);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String response = bufferedReader.readLine();
        return response.equals("true");
    }

    public static boolean cadastrarUsuario(Context context, String nome, String senha, String email) throws IOException {
        String values = "nome=" + nome + "&email=" + email + "&senha=" + senha;
        String url = DB_URL + "ws_insert/register.php?" + values;
        return insertIntoURL(url, context);
    }

    public static JSONObject login(String email, String senha, Context context) throws IOException, JSONException{
        String values = "email=" + email + "&senha=" + senha;
        String url = DB_URL + "ws_read/login.php?" + values;
        JSONArray jsonArray = null;
        jsonArray = readJSONFromURL(url, context);
        if(jsonArray == null){
            return null;
        } else {
            return (JSONObject) jsonArray.get(0);
        }
    }

    public static boolean enviarVitoria (Context context) throws IOException {
        String values = "email=" + Usuario.getEmail() + "&tempo=" + PS.getTempoTotal() + "&movimentos=" + PS.getNumeroAcoes();
        String url = DB_URL + "ws_insert/register_vitoria.php?" + values;
        return insertIntoURL(url, context);
    }

    public static boolean enviarDerrota (Context context) throws IOException{
        String values = "email=" + Usuario.getEmail() + "&tempo=" + PS.getTempoTotal();
        String url = DB_URL + "ws_update/update_tempo.php?" + values;
        return insertIntoURL(url, context);
    }

    public static JSONArray getRankingByTime(Context context) throws JSONException, IOException{
        return readJSONFromURL(DB_URL + "ws_read/ranking_t.php", context);
    }

    public static JSONArray getRankingByMovimentos(Context context) throws JSONException, IOException{
        return readJSONFromURL(DB_URL + "ws_read/ranking_m.php", context);
    }

    public static JSONArray getRankingPessoalByTime(Context context) throws JSONException, IOException{
        return readJSONFromURL(DB_URL + "ws_read/ranking_pt.php?email=" + Usuario.getEmail(), context);
    }

    public static JSONArray getRankingPessoalByMovimentos(Context context) throws JSONException, IOException{
        return readJSONFromURL(DB_URL + "ws_read/ranking_pm.php?email=" + Usuario.getEmail(), context);
    }

    public static JSONArray getRankingPessoalByOrdemCronologica(Context context) throws JSONException, IOException{
        return readJSONFromURL(DB_URL + "ws_read/ranking_pc.php?email=" + Usuario.getEmail(), context);
    }

    private static void checkThreadPolicy(){
        int SDK_INT = Build.VERSION.SDK_INT;
        if(SDK_INT > 8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    private static boolean checkNetworkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static boolean excluirUsuario(Context contexto) throws IOException{
        return insertIntoURL(DB_URL + "ws_delete/delete_usuario.php?email=" + Usuario.getEmail(), contexto);
    }
}
