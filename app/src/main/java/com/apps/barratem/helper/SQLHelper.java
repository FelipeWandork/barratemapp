package com.apps.barratem.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.apps.barratem.model.BancoDeDados;

public class SQLHelper extends SQLiteOpenHelper {

    private AnuncioDAO anuncioDAO;

    public static String NAME = "barratemDB";
    public static String TABELA_ANUNCIOS = "tb_anuncios";
    public static  int VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        BancoDeDados bancoDeDados = new BancoDeDados();

        // TABELA DE ANÚNCIOS
        // Podem existir diferenças comparada a tabela original da web, pois só estou trazendo para o app o que é relevante para o funcionamento do app
        Log.i("BD", "Criando a tabela tb_anuncios");

        String sql_anuncios = "CREATE TABLE IF NOT EXISTS tb_anuncios (" +
                "  id int NOT NULL," +
                "  empresa text NOT NULL," +
                "  descricao text NOT NULL," +
                "  email text NOT NULL," +
                "  endereco text NOT NULL," +
                "  numero int(5) NOT NULL," +
                "  complemento text NOT NULL," +
                "  bairro text NOT NULL," +
                "  cidade text NOT NULL," +
                "  uf text NOT NULL," +
                "  cep text NOT NULL," +
                "  telefone1 text NOT NULL," +
                "  telefone2 text NOT NULL," +
                "  telefone3 text NOT NULL," +
                "  telefone4 text NOT NULL," +
                "  latitude text NOT NULL," +
                "  longitude text NOT NULL," +
                "  status varchar(3) DEFAULT NULL," +
                "  categoria text NOT NULL," +
                "  facebook varchar(100) NOT NULL," +
                "  instagram varchar(20) NOT NULL," +
                "  website varchar(100) NOT NULL," +
                "  whatsapp_1 tinyint(1) DEFAULT NULL," +
                "  whatsapp_2 tinyint(1) DEFAULT NULL," +
                "  whatsapp_3 tinyint(1) DEFAULT NULL," +
                "  whatsapp_4 tinyint(1) DEFAULT NULL);";

        sqLiteDatabase.execSQL(sql_anuncios);

        // TABELA DE CATEGORIAS
        // Podem existir diferenças comparada a tabela original da web, pois só estou trazendo para o app o que é relevante
        Log.i("BD", "Criando a tabela tb_categorias");
        String sql_categorias = "CREATE TABLE IF NOT EXISTS tb_categorias ( " +
                        " id INTEGER NOT NULL, " +
                        " categoria tinytext NOT NULL, " +
                        " palavras text NOT NULL, " +
                        " descricao text NOT NULL);";

        sqLiteDatabase.execSQL(sql_categorias);


        // TABELA DE PRÉ CADASTRO
        // Podem existir diferenças comparada a tabela original da web, pois só estou trazendo para o app o que é relevante
        Log.i("BD", "Criando a tabela tb_precadastro");
        String sql_precadastro = "CREATE TABLE IF NOT EXISTS tb_precadastro (" +
                                      " id int(4) NOT NULL," +
                                      " empresa text NOT NULL," +
                                      " descricao text NOT NULL," +
                                      " endereco text NOT NULL," +
                                      " numero int(5) NOT NULL," +
                                      " bairro text NOT NULL," +
                                      " cep text NOT NULL," +
                                      " telefone1 text NOT NULL," +
                                      " telefone2 text NOT NULL," +
                                      " email text NOT NULL," +
                                      " time varchar(100) NOT NULL," +
                                      " facebook varchar(100) DEFAULT NULL," +
                                      " instagram varchar(20) DEFAULT NULL," +
                                      " website varchar(100) DEFAULT NULL);";

        sqLiteDatabase.execSQL(sql_categorias);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {



    }

    @Override
    public SQLiteDatabase getWritableDatabase() {


        return super.getWritableDatabase();
    }
}
