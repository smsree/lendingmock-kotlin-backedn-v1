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
import { useState } from 'react';
import {toast} from 'react-toastify'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href='/about'>
        Lending Mock
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

export default function Register() {

  const baseUrl = "http://localhost:8091/v1/customer/register"

  const navigate = useNavigate()

  const [customer, setCustomer] = useState({
    name:"",
    phoneNumber:"",
    email:"",
    dateOfBirth:"",
    adhar:"",
    panNumber:""
  })

  const handleInput = (e) =>{
    setCustomer({...customer,[e.target.name]:e.target.value})
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post(baseUrl,customer)
    .then(response =>{
      console.log(response.data)
      toast.success("Customer Registered Successfuly")
      navigate("/")
    }).catch(Error =>{
      console.log("Error",Error)
      toast.error("Some Error Occured :(")
    })
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
            Sign up
          </Typography>
          <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  name="name"
                  required
                  fullWidth
                  type="text"
                  id="name"
                  value={customer.name}
                  label="Full Name"
                  onChange={handleInput}
                  autoFocus
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
                  value={customer.dateOfBirth}
                  onChange={handleInput}
                  
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="phoneNumber"
                  label="Phone Number"
                  name="phoneNumber"
                  type="text"
                  value={customer.phoneNumber}
                  onChange={handleInput}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  type="email"
                  value={customer.email}
                  onChange={handleInput}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="adhar"
                  label="Aadhaar Number"
                  type="text"
                  id="adhar"
                  value={customer.adhar}
                  onChange={handleInput}                  
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="panNumber"
                  label="PAN Number"
                  type="text"
                  id="panNumber"
                  value={customer.panNumber}
                  onChange={handleInput}                  
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
    </>
  );
}