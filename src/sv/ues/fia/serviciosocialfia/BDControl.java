package sv.ues.fia.serviciosocialfia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDControl extends SQLiteOpenHelper {

	// Nombre de nuestro archivo de base de datos
	private static final String NOMBRE_BD = "SSBD.s3db";

	// Versión de nuestra base de datos
	private static final int VERSION_BD = 1;

	// TABLAS DE LA BASE DE DATOS

	// Tabla AlumnoExpediente
	private static final String TABLA_ALUMNO_EXPEDIENTE = "create table ALUMNOEXPEDIENTE "
			+ "(IDEXPEDIENTE         CHAR(10)             not null,"
			+ "IDBITACORA           CHAR(10)             not null,"
			+ "CARNETEMPLEADO       CHAR(7),"
			+ "CODCARRERA           CHAR(4)              not null,"
			+ "CARNETALUMNO         CHAR(7)              not null,"
			+ "NOMBREALUMNO         CHAR(30)             not null,"
			+ "APELLIDOALUMNO       CHAR(30)             not null,"
			+ "SEXOALUMNO           CHAR(1)              not null,"
			+ "FECHAINICIOSERVICIO  CHAR(10)             not null,"
			+ "FECHAFINSERVICIO     CHAR(10),"
			+ "ESTADOALUMNO         CHAR(1)              not null,"
			+ "TELEFONO             CHAR(9),"
			+ "EMAIL                CHAR(15),"
			+ "OBSERVACIONES        CHAR(100),"
			+ "VALORSERVICIO        FLOAT,"
			+ "HORASACUMULA         INTEGER,"
			+ "FECHAACUMULA         CHAR(10),"
			+ "constraint PK_ALUMNOEXPEDIENTE primary key (IDEXPEDIENTE));";

	// Tabla Beneficiario
	private static final String TABLA_BENEFICIARIO = "create table BENEFICIARIO "
			+ "(IDBENEFICIARIO       CHAR(10)             not null,"
			+ "CARNETEMPLEADO       CHAR(7)              not null,"
			+ "NOMBREORGANIZACION   CHAR(50)             not null,"
			+ "NOMBREREPRESENTANTE  CHAR(30)             not null,"
			+ "APELLIDOREPRESENTANTE CHAR(30)             not null,"
			+ "TELEFONOBENEF        INTEGER,"
			+ "DIRECCIONBENEF       CHAR(50),"
			+ "EMAIL                CHAR(15),"
			+ "constraint PK_BENEFICIARIO primary key (IDBENEFICIARIO));";

	// Tabla Bitacora
	private static final String TABLA_BITACORA = "create table BITACORA "
			+ "(IDBITACORA           CHAR(10)             not null,"
			+ "IDEXPEDIENTE         CHAR(10),"
			+ "IDPROYECTO           CHAR(10)             not null,"
			+ "CODIGOTUTOR          CHAR(7)              not null,"
			+ "CANTIDADHORAS        INTEGER              not null,"
			+ "FECHAACTIVIDAD       CHAR(10)             not null,"
			+ "ESTADOACTIVIDAD      CHAR(1)              not null,"
			+ "FECHAAUTORIZADO      CHAR(10),"
			+ "HORAINICIO           CHAR(5)              not null,"
			+ "HORAFIN              CHAR(5)              not null,"
			+ "VALORACTIVIDAD       FLOAT," + "PRECIOTRABAJO        FLOAT,"
			+ "constraint PK_BITACORA primary key (IDBITACORA));";

	// Tabla Carreras
	private static final String TABLA_CARRERAS = "create table CARRERAS "
			+ "(CODCARRERA           CHAR(4)              not null,"
			+ "CODESCUELA           CHAR(30)             not null,"
			+ "NOMBRECARRERA        CHAR(25)             not null,"
			+ "constraint PK_CARRERAS primary key (CODCARRERA));";

	// Tabla Directorss
	private static final String TABLA_DIRECTORSS = "create table DIRECTORSS "
			+ "(CARNETEMPLEADO       CHAR(7)              not null,"
			+ "CODESCUELA           CHAR(30),"
			+ "NOMBREDIRECTOR       CHAR(30),"
			+ "APELLIDODIRECTOR     CHAR(30),"
			+ "SEXODIRECTOR         CHAR(1),"
			+ "FECHAINICIO          CHAR(10)              not null,"
			+ "FECHAFIN             CHAR(10),"
			+ "constraint PK_DIRECTORSS primary key (CARNETEMPLEADO));";

	// Tabla Escuela
	private static final String TABLA_ESCUELA = "create table ESCUELA "
			+ "(CODESCUELA           CHAR(30)             not null,"
			+ "CARNETEMPLEADO       CHAR(7),"
			+ "NOMBREESCUELA        CHAR(15)             not null,"
			+ "constraint PK_ESCUELA primary key (CODESCUELA));";

	// Tabla Informe
	private static final String TABLA_INFORME = "create table INFORME "
			+ "(CODIGOTUTOR          CHAR(7)              not null,"
			+ "IDEXPEDIENTE         CHAR(10)             not null,"
			+ "CORRINFORME          INTEGER              not null,"
			+ "FECHAENTREGA         CHAR(10)             not null,"
			+ "FECHAAUTORIZACION    CHAR(10),"
			+ "OBJETIVOALCANZADO    CHAR(50)             not null,"
			+ "COMENTARIOS          CHAR(50),"
			+ "TIPOINFORME          CHAR(1)              not null,"
			+ "ESTADO               CHAR(2)              not null,"
			+ "constraint PK_INFORME primary key (CODIGOTUTOR));";

	// Tabla Precios
	private static final String TABLA_PRECIOS = "create table PRECIOS "
			+ "(CORR                 CHAR(2)              not null,"
			+ "IDBITACORA           CHAR(10)             not null,"
			+ "IDTIPODETRABAJO      CHAR(10)             not null,"
			+ "PRECIO               FLOAT                not null,"
			+ "FECHAINICIALAPLIPRE  CHAR(10)             not null,"
			+ "FECHAFINALAPLIPRE    CHAR(10),"
			+ "OBSERVACION          CHAR(25),"
			+ "constraint PK_PRECIOS primary key (CORR));";

	// Tabla Proyecto
	private static final String TABLA_PROYECTO = "create table PROYECTO "
			+ "(IDPROYECTO           CHAR(10)             not null,"
			+ "IDBENEFICIARIO       CHAR(10)             not null,"
			+ "CARNETEMPLEADO       CHAR(7),"
			+ "IDEXPEDIENTE         CHAR(10),"
			+ "IDTIPOPROYECTO       CHAR(10),"
			+ "NOMBREDEPROYECTO     CHAR(100)            not null,"
			+ "DESCRIPCIONPROYECTO  CHAR(100)            not null,"
			+ "DURACIONPROYECTO     FLOAT,"
			+ "FECHAINICIOPROY      CHAR(10)             not null,"
			+ "FECHAFINPROY         CHAR(10),"
			+ "ESTADOPROYECTO       CHAR(1)              not null,"
			+ "VALORPROYECTO        FLOAT,"
			+ "constraint PK_PROYECTO primary key (IDPROYECTO));";

	// Tabla TipoDeProyecto
	private static final String TABLA_TIPO_DE_PROYECTO = "create table TIPO_DE_PROYECTO "
			+ "(IDTIPOPROYECTO       CHAR(10)             not null,"
			+ "IDPROYECTO           CHAR(10)             not null,"
			+ "MODALIDADPROYECTO    CHAR(100)            not null,"
			+ "constraint PK_TIPO_DE_PROYECTO primary key (IDTIPOPROYECTO));";

	// Tabla TipoDeTrabajo
	private static final String TABLA_TIPO_DE_TRABAJO = "create table TIPO_DE_TRABAJO "
			+ "(IDBITACORA           CHAR(10)             not null,"
			+ "IDTIPODETRABAJO      CHAR(10)             not null,"
			+ "CORR                 CHAR(2),"
			+ "NOMBRETIPO           CHAR(30)             not null,"
			+ "constraint PK_TIPO_DE_TRABAJO primary key (IDBITACORA, IDTIPODETRABAJO));";

	// Tabla Tutor
	private static final String TABLA_TUTOR = "create table TUTOR "
			+ "(CODIGOTUTOR          CHAR(7)              not null,"
			+ "IDBENEFICIARIO       CHAR(10)             not null,"
			+ "NOMBRETUTOR          CHAR(30)             not null,"
			+ "APELLIDOTUTOR        CHAR(30)             not null,"
			+ "SEXOTUTOR            CHAR(1)              not null,"
			+ "constraint PK_TUTOR primary key (CODIGOTUTOR));";

	// FIN DE TABLAS DE LA BASE DE DATOS

	// Constructor
	public BDControl(Context context) {
		super(context, NOMBRE_BD, null, VERSION_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Creación de las tablas
		db.execSQL(TABLA_ALUMNO_EXPEDIENTE);
		db.execSQL(TABLA_BENEFICIARIO);
		db.execSQL(TABLA_BITACORA);
		db.execSQL(TABLA_CARRERAS);
		db.execSQL(TABLA_DIRECTORSS);
		db.execSQL(TABLA_ESCUELA);
		db.execSQL(TABLA_INFORME);
		db.execSQL(TABLA_PRECIOS);
		db.execSQL(TABLA_PROYECTO);
		db.execSQL(TABLA_TIPO_DE_PROYECTO);
		db.execSQL(TABLA_TIPO_DE_TRABAJO);
		db.execSQL(TABLA_TUTOR);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_ALUMNO_EXPEDIENTE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_BENEFICIARIO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_BITACORA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_CARRERAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_DIRECTORSS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_ESCUELA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_INFORME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_PRECIOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROYECTO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_TIPO_DE_PROYECTO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_TIPO_DE_TRABAJO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLA_TUTOR);
		onCreate(db);
	}

	// Funciones de inserción, actualización y eliminación de las tablas

	// FUNCIONES DE INSERCIÓN DE DATOS
	public String insertar(AlumnoExpediente alumExp){
		
		//Abriendo la base de datos
		SQLiteDatabase db = getWritableDatabase();
		
		//Variables para controlar los registros insertados
		long contador = 0;
		String registrosInsertados = "Registros insertado # = ";
		
		if(db != null){
			ContentValues valores = new ContentValues();
			valores.put("IDEXPEDIENTE", alumExp.getIdExpediente());
			valores.put("IDBITACORA", alumExp.getIdBitacora());
			valores.put("CARNETEMPLEADO", alumExp.getCarnetEmpleado());
			valores.put("CODCARRERA", alumExp.getCodCarrera());
			valores.put("CARNETALUMNO", alumExp.getCarnet());
			valores.put("NOMBREALUMNO", alumExp.getNombre());
			valores.put("APELLIDOALUMNO", alumExp.getApellido());
			valores.put("SEXOALUMNO", alumExp.getSexo());
			valores.put("FECHAINICIOSERVICIO", alumExp.getFechaInicioServicio());
			valores.put("FECHAFINSERVICIO", alumExp.getFechaFinServicio());
			valores.put("ESTADOALUMNO", alumExp.getEstado());
			valores.put("TELEFONO", alumExp.getTelefono());
			valores.put("EMAIL", alumExp.getEmail());
			valores.put("OBSERVACIONES", alumExp.getObservaciones());
			valores.put("VALORSERVICIO", alumExp.getValorServicio());
			valores.put("HORASACUMULA", alumExp.getHorasAcumula());
			valores.put("FECHAACUMULA", alumExp.getFechaAcumula());
			contador = db.insert("ALUMNOEXPEDIENTE", null, valores);
			
			//Cerrando base de datos
			db.close();
			
			if(contador==-1 || contador==0){
				registrosInsertados= "Error al Insertar el registro, Registro"
						+ "Duplicado. Verificar inserción";
			}
			else {
			registrosInsertados=registrosInsertados+contador;
			}
			return registrosInsertados;
		}
		return "La Base de Datos no existe";
	}

	public String insertar(Beneficiario beneficiario) {
		return null;
	}

	public String insertar(Bitacora bitacora) {
		return null;
	}

	public String insertar(Carreras carreras) {
		return null;
	}

	public String insertar(DirectorSS directorss) {
		return null;
	}

	public String insertar(Escuela escuela) {
		return null;
	}

	public String insertar(Informe informe) {
		return null;
	}

	public String insertar(Precios precios) {
		return null;
	}

	public String insertar(Proyecto proyecto) {
		return null;
	}

	public String insertar(TipoDeProyecto tipoDeProyecto) {
		return null;
	}

	public String insertar(TipoDeTrabajo tipoDeTrabajo) {
		return null;
	}

	public String insertar(Tutor tutor) {
		//Abriendo la base de datos
				SQLiteDatabase db = getWritableDatabase();
				
				//Variables para controlar los registros insertados
				long contador = 0;
				String registrosInsertados = "Registros insertado # = ";
				
				if(db != null){
					ContentValues valores = new ContentValues();
					valores.put("CODIGOTUTOR", tutor.getCodigoTutor());
					valores.put("IDBENEFICIARIO", tutor.getIdBeneficiario());
					valores.put("NOMBRETUTOR", tutor.getNombre());
					valores.put("APELLIDOTUTOR", tutor.getApellido());
					valores.put("SEXOTUTOR", tutor.getSexo());
					
					//Cerrando base de datos
					db.close();
					
					if(contador==-1 || contador==0){
						registrosInsertados= "Error al Insertar el registro, Registro"
								+ "Duplicado. Verificar inserción";
					}
					else {
					registrosInsertados=registrosInsertados+contador;
					}
					return registrosInsertados;
				}
				return "La Base de Datos no existe";
	}

	// FIN FUNCIONES DE INSERCIÓN DE DATOS

	// FUNCIONES DE ACTUALIZACIÓN DE DATOS
	public String actualizar(AlumnoExpediente alumExp) {
		return null;
	}

	public String actualizar(Beneficiario beneficiario) {
		return null;
	}

	public String actualizar(Bitacora bitacora) {
		return null;
	}

	public String actualizar(Carreras carreras) {
		return null;
	}

	public String actualizar(DirectorSS directorss) {
		return null;
	}

	public String actualizar(Escuela escuela) {
		return null;
	}

	public String actualizar(Informe informe) {
		return null;
	}

	public String actualizar(Precios precios) {
		return null;
	}

	public String actualizar(Proyecto proyecto) {
		return null;
	}

	public String actualizar(TipoDeProyecto tipoDeProyecto) {
		return null;
	}

	public String actualizar(TipoDeTrabajo tipoDeTrabajo) {
		return null;
	}

	public String actualizar(Tutor tutor) {
		return null;
	}

	// FIN FUNCIONES DE ACTUALIZACIÓN DE DATOS

	// FUNCIONES DE ELIMINACIÓN DE DATOS
	public String eliminar(AlumnoExpediente alumExp) {
		return null;
	}

	public String eliminar(Beneficiario beneficiario) {
		return null;
	}

	public String eliminar(Bitacora bitacora) {
		return null;
	}

	public String eliminar(Carreras carreras) {
		return null;
	}

	public String eliminar(DirectorSS directorss) {
		return null;
	}

	public String eliminar(Escuela escuela) {
		return null;
	}

	public String eliminar(Informe informe) {
		return null;
	}

	public String eliminar(Precios precios) {
		return null;
	}

	public String eliminar(Proyecto proyecto) {
		return null;
	}

	public String eliminar(TipoDeProyecto tipoDeProyecto) {
		return null;
	}

	public String eliminar(TipoDeTrabajo tipoDeTrabajo) {
		return null;
	}

	public String eliminar(Tutor tutor) {
		return null;
	}
	// FIN FUNCIONES DE ELIMINACIÓN DE DATOS

}
