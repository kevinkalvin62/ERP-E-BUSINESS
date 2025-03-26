const express = require('express');
const mysql = require('mysql2');
const jwt = require('jsonwebtoken');
const cors = require('cors');
const bodyParser = require('body-parser');
const qr = require('qrcode');

const app = express();
const port = 3000;

app.use(cors());
app.use(bodyParser.json());

/*AQUI VAN LOS DETALLES DE SUS MAQUINAS LOCALES,NO MODIFICAR NADA*/
const db = mysql.createConnection({
  host: '192.168.18.24',
  user: 'node_user_erp',
  password: 'Prueba123*',
  database: 'ERPBUSINESS',
});
  
app.listen(3000, '0.0.0.0', () => {
  console.log('Server running on port 3000');
});

//asegurandome que los cambios si sirven y no solo es codigo al azar asi alav



// Role constants
const ROLES = {
  MOSTRADOR: '1',
  INVENTARIOS: '2',
  ADMINISTRATIVO: '3',
  ADMIN: '99'
};

// Authentication middleware
const authenticateToken = (req, res, next) => {
  const token = req.headers['authorization']?.split(' ')[1];
  if (!token) return res.status(401).json({ message: 'Authentication required' });


  jwt.verify(token, 'aVeryStrongSecretKeyHere', (err, user) => {
    if (err) return res.status(403).json({ message: 'Invalid token' });
    req.user = user;
    next();
  });

};

// Role check middleware
const checkRole = (roles) => {
  return (req, res, next) => {
    if (!roles.includes(req.user.user_role)) {
      return res.status(403).json({ message: 'Access denied' });
    }
    next();
  };
};


app.post('/login', (req, res) => {
  const { username, password } = req.body;

  console.log(username,"                  ",password);

  db.query('SELECT USUARIOS.ID_USR, USUARIOS.PASS,USUARIOS_DETAIL.NOMBRE , USUARIOS.ROLEE FROM USUARIOS  JOIN USUARIOS_DETAIL ON USUARIOS.ID_USR=USUARIOS_DETAIL.ID_USR WHERE USUARIOS.ID_USR = ?; ', [username], (err, result) => {
    if (err) return res.status(500).json({ message: 'Database error' });

    console.log(result);

    if (result.length === 0 || result[0].PASS !== password) {
      console.log(result);
      return res.status(401).json({ message: 'Invalid credentials' });
    }

    console.log("errores en la autenticacion");
    const user = result[0];
    const token = jwt.sign({
      user_id: user.ID_USR,
      user_name: user.NOMBRE,
      user_role: user.ROLEE,
      user_matricula: user.ID_USR
      },
      'aVeryStrongSecretKeyHere',
      { expiresIn: '1h' }
    );

    res.json({
      token,
      user_role: user.ROLEE,
      user_id: user.ID_USR,
      user_matricula: user.ID_USR
    });
  });
});