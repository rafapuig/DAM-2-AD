import java.sql.*;

public class Metadatos {

    public static void main(String... args) {

        //String url = "jdbc:sqlite:C:/AD/sqlite/ejemplo.db";
        //String esquemaPattern = "Ejemplo";

        String url = "jdbc:hsqldb:file:C:/AD/hsqldb/ejemplo";
        String esquemaPattern = "PUBLIC";

        //String url = "jdbc:derby:C:/AD/derby/ejemplo";
        //String esquemaPattern = "APP";

        try {
            Connection conn = DriverManager.getConnection(url);
            DatabaseMetaData dbmd = conn.getMetaData();
            //Informacion general
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String URL = dbmd.getURL();
            String usuario = dbmd.getUserName();
            System.out.println("INFORMACION SOBRE LA BASE DE DATOS");
            System.out.println("----------------------------------");
            System.out.println("Nombre: " + nombre);
            System.out.println("Driver: " + driver);
            System.out.println("URL: " + URL);
            System.out.println("User: " + usuario);

            //Informacion sobre las tablas
            ResultSet rsTablas = dbmd.getTables(null, esquemaPattern, null, null);
            System.out.println("Metadatos de TABLAS:");
            ResultSetMetaData rsmd = rsTablas.getMetaData();
            int nc = rsmd.getColumnCount();
            for (int i = 1; i <= nc; i++) {
                //System.out.println(rsmd.getColumnName(i));
            }
            while (rsTablas.next()) {
                String catalogo = rsTablas.getString(1);
                String esquema = rsTablas.getString(rsmd.getColumnName(2));
                String tabla = rsTablas.getString(3);
                String tipo = rsTablas.getString(4);
                System.out.println("Catalogo:" + catalogo + " Esquema:" + esquema + " Tipo:" + tipo + " Tabla:" + tabla);

                ResultSet rsColumnas = dbmd.getColumns(null, esquema, tabla, null);

                System.out.println("Metadatos de COLUMNAS:");
                ResultSetMetaData colsmd = rsColumnas.getMetaData();
                nc = colsmd.getColumnCount();
                for (int i = 1; i <= nc; i++) {
                    //System.out.println(colsmd.getColumnName(i));
                }
                System.out.println("COLUMNAS DE LA TABLA: " + tabla);
                System.out.println("----------------------------------");
                while (rsColumnas.next()) {
                    String nombreCol = rsColumnas.getString("COLUMN_NAME");
                    String tipoCol = rsColumnas.getString("TYPE_NAME");
                    String tamCol = rsColumnas.getString("COLUMN_SIZE");
                    String anulable = rsColumnas.getString("IS_NULLABLE");
                    System.out.println("Columna:" + nombreCol + " tipo:" + tipoCol + " tamaño:" + tamCol + " Anulable:" + anulable);
                }
                rsColumnas.close();

                ResultSet pk = dbmd.getPrimaryKeys(null, esquema, tabla);
                System.out.println("Metadatos de CLAVE PRIMARIA:");
                ResultSetMetaData pkmd = pk.getMetaData();
                nc = pkmd.getColumnCount();
                for (int i = 1; i <= nc; i++) {
                    //System.out.println(pkmd.getColumnName(i));
                }
                StringBuilder primaryKey = new StringBuilder();
                while (pk.next()) {
                    if (!primaryKey.isEmpty()) {
                        primaryKey.append(" - ");
                    }
                    primaryKey.append(pk.getString("COLUMN_NAME"));
                }
                pk.close();
                System.out.println("Clave primaria: " + primaryKey);

                System.out.println("COLUMNAS QUE APUNTAN A LA CLAVE PRIMARIA de la TABLA " + tabla);

                ResultSet fk = dbmd.getExportedKeys(null,esquema,tabla);
                System.out.println("Metadatos de CLAVES AJENAS:");
                ResultSetMetaData fkmd = fk.getMetaData();
                nc = fkmd.getColumnCount();
                for (int i = 1; i <= nc; i++) {
                    //System.out.println(fkmd.getColumnName(i));
                }

                while(fk.next()){
                    String fk_col = fk.getString("FKCOLUMN_NAME");
                    String pk_col = fk.getString("PKCOLUMN_NAME");
                    String fk_tabla = fk.getString("FKTABLE_NAME");
                    String pk_tabla = fk.getString("PKTABLE_NAME");
                    System.out.println("Tabla hija:"+fk_tabla+" Clave ajena:"+fk_col);
                    System.out.println("Tabla padre:"+pk_tabla+" Clave principal:"+pk_col);
                }
                fk.close();
                System.out.println();
            }
            rsTablas.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
