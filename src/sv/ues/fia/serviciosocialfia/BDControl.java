package sv.ues.fia.serviciosocialfia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDControl extends SQLiteOpenHelper {

	//Nombre de nuestro archivo de base de datos
	private static final String NOMBRE_BD = "SSBD.s3db";
	
	//Versi�n de nuestra base de datos
	private static final int VERSION_BD = 1;
	
	//TABLAS DE LA BASE DE DATOS
	
	//Tabla AlumnoExpediente
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
	
	//Tabla Beneficiario
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
	
	//Tabla Bitacora
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
			+ "VALORACTIVIDAD       FLOAT,"
			+ "PRECIOTRABAJO        FLOAT,"
			+ "constraint PK_BITACORA primary key (IDBITACORA));";
	
	//Tabla Carreras
	private static final String TABLA_CARRERAS = "create table CARRERAS "
			+ "(CODCARRERA           CHAR(4)              not null,"
			+ "CODESCUELA           CHAR(30)             not null,"
			+ "NOMBRECARRERA        CHAR(25)             not null,"
			+ "constraint PK_CARRERAS primary key (CODCARRERA));";
	
	//Tabla Directorss
	private static final String TABLA_DIRECTORSS = "create table DIRECTORSS "
			+ "(CARNETEMPLEADO       CHAR(7)              not null,"
			+ "CODESCUELA           CHAR(30),"
			+ "NOMBREDIRECTOR       CHAR(30),"
			+ "APELLIDODIRECTOR     CHAR(30),"
			+ "SEXODIRECTOR         CHAR(1),"
			+ "FECHAINICIO          CHAR(10)              not null,"
			+ "FECHAFIN             CHAR(10),"
			+ "constraint PK_DIRECTORSS primary key (CARNETEMPLEADO));";
	
	//Tabla Escuela
	private static final String TABLA_ESCUELA = "create table ESCUELA "
			+ "(CODESCUELA           CHAR(30)             not null,"
			+ "CARNETEMPLEADO       CHAR(7),"
			+ "NOMBREESCUELA        CHAR(15)             not null,"
			+ "constraint PK_ESCUELA primary key (CODESCUELA));";
	
	//Tabla Informe
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
		
	//Tabla Precios
	private static final String TABLA_PRECIOS = "create table PRECIOS "
			+ "(CORR                 CHAR(2)              not null,"
			+ "IDBITACORA           CHAR(10)             not null,"
			+ "IDTIPODETRABAJO      CHAR(10)             not null,"
			+ "PRECIO               FLOAT                not null,"
			+ "FECHAINICIALAPLIPRE  CHAR(10)             not null,"
			+ "FECHAFINALAPLIPRE    CHAR(10),"
			+ "OBSERVACION          CHAR(25),"
			+ "constraint PK_PRECIOS primary key (CORR));";
		
	//Tabla Proyecto
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
	
	//Tabla TipoDeProyecto
	private static final String TABLA_TIPO_DE_PROYECTO = "create table TIPO_DE_PROYECTO "
			+ "(IDTIPOPROYECTO       CHAR(10)             not null,"
			+ "IDPROYECTO           CHAR(10)             not null,"
			+ "MODALIDADPROYECTO    CHAR(100)            not null,"
			+ "constraint PK_TIPO_DE_PROYECTO primary key (IDTIPOPROYECTO));";
	
	//Tabla TipoDeTrabajo
	private static final String TABLA_TIPO_DE_TRABAJO = "create table TIPO_DE_TRABAJO "
			+ "(IDBITACORA           CHAR(10)             not null,"
			+ "IDTIPODETRABAJO      CHAR(10)             not null,"
			+ "CORR                 CHAR(2),"
			+ "NOMBRETIPO           CHAR(30)             not null,"
			+ "constraint PK_TIPO_DE_TRABAJO primary key (IDBITACORA, IDTIPODETRABAJO));";
	
	//Tabla Tutor
	private static final String TABLA_TUTOR = "create table TUTOR "
			+ "(CODIGOTUTOR          CHAR(7)              not null,"
			+ "IDBENEFICIARIO       CHAR(10)             not null,"
			+ "NOMBRETUTOR          CHAR(30)             not null,"
			+ "APELLIDOTUTOR        CHAR(30)             not null,"
			+ "SEXOTUTOR            CHAR(1)              not null,"
			+ "constraint PK_TUTOR primary key (CODIGOTUTOR));";
	
	//FIN DE TABLAS DE LA BASE DE DATOS
	
	
	//Constructor
	public BDControl(Context context, String name, CursorFactory factory,
			int version) {
		super(context, NOMBRE_BD, null, VERSION_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Creaci�n de las tablas
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
		//Creaci�n de las tablas
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


}
