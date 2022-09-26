import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import DefaultNavbar from './navigation/DefaultNavbar';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import {toast} from 'react-toastify';
import axios from 'axios';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Lending Mock
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

export default function Login() {
  const baseUrl = "http://localhost:8091/v1/customer"
  const navigate = useNavigate()
  const [login, setLogin] = useState({
    phoneNumber:"",
    dateOfBirth:"2022-09-25"
  })

  const handleInput = (e) =>{
    setLogin({...login,[e.target.name]:e.target.value})
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    if(login.phoneNumber==="" || login.dateOfBirth===""){
      toast.error("The Text fields cannot be blank!!!")
    }
    else if(isNaN(login.phoneNumber) || (login.phoneNumber.length<10 || login.phoneNumber.length>10)){
      toast.error("Please enter a valid Phone Number")
    }
    else{
      axios.post(baseUrl+'/login',login)
      .then(response =>{
        console.log(response.data)
        if(response.data){
          toast.success("Login Successful")
          sessionStorage.setItem("phoneNumber",response.data.phoneNumber)
          sessionStorage.setItem("name",response.data.name)
          sessionStorage.setItem("adhar",response.data.adhar)
          sessionStorage.setItem("panNumber",response.data.panNumber)
          sessionStorage.setItem("dateOfBirth",response.data.dateOfBirth)
          sessionStorage.setItem("email",response.data.email)
          navigate("/offer")
        }
      }).catch(Error =>{
        console.log("Error",Error)
        toast.error("Some Error Occured :(")
      })
    }
  };

  return (
    <>
    <DefaultNavbar/>
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }} noValidate>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  name="phoneNumber"
                  required
                  fullWidth
                  id="phoneNumber"
                  label="Phone Number"
                  onChange={handleInput}
                  value={login.phoneNumber}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="dateOfBirth"
                  label="Date of Birth"
                  name="dateOfBirth"
                  type="date"
                  onChange={handleInput}
                  value={login.dateOfBirth}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/register" variant="body2">
                  Dnon't have an account? Sign up
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt:25 }} />
      </Container>
    </ThemeProvider>
    </>
  );
}