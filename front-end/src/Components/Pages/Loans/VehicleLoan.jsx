import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import DriveEtaIcon from '@mui/icons-material/DriveEta';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import UserNavbar from '../navigation/UserNavbar';
import { useState} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {toast} from 'react-toastify'


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



export default function VehicleLoan() {

  const navigate = useNavigate()

  const baseUrl = "http://localhost:8082/v1/vehicleLoan/"

  const [vehicle,setVehicle] = useState({
    vehicleName:"",
    customerMobileNo:sessionStorage.getItem("phoneNumber"),
    loanName:sessionStorage.getItem("loanName"),
    loanamount:sessionStorage.getItem("loanAmount"),
    rateOfInterest:sessionStorage.getItem("rateOfInterest")
  })

  const handleChange = (e) =>{
    setVehicle({...vehicle,[e.target.name]:e.target.value})
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    axios.post(baseUrl,vehicle)
    .then((response)=>{
      console.log(response.data)
      if(response.data){
        toast.success("Applied for Vehicle Loan")
        navigate("/status")
      }
    })
  };

  return (
    <>
    <UserNavbar/>
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
            <DriveEtaIcon/>
          </Avatar>
          <Typography component="h1" variant="h5">
            Vehicle Loan Application Form
          </Typography>
          <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}  sm={6}>
                <TextField
                  autoComplete="given-name"
                  name="name"
                  required
                  fullWidth
                  id="name"
                  label="Full Name"
                  value={sessionStorage.getItem("name")}
                  InputProps={{
                    readOnly: true,
                  }}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  id="dateOfBirth"
                  label="Date of Birth"
                  name="dateOfBirth"
                  type="date"
                  value={sessionStorage.getItem("dateOfBirth")}
                  InputProps={{
                    readOnly: true,
                  }}
                  
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  id="phoneNumber"
                  label="Phone Number"
                  name="phoneNumber"
                  type="number"
                  InputProps={{
                    readOnly: true,
                  }}
                  value={sessionStorage.getItem("phoneNumber")}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  type="email"
                  InputProps={{
                    readOnly: true,
                  }}
                  value={sessionStorage.getItem("email")}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="adhar"
                  label="Aadhaar Number"
                  type="number"
                  id="adhar"
                  InputProps={{
                    readOnly: true,
                  }}
                  value={sessionStorage.getItem("adhar")}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="panNumber"
                  label="PAN Number"
                  type="text"
                  id="panNumber"
                  InputProps={{
                    readOnly: true,
                  }}
                  value={sessionStorage.getItem("panNumber")}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="rateOfInterest"
                  label="Intrest rate"
                  type="text"
                  id="rateOfInterest"
                  InputProps={{
                    readOnly: true,
                  }}
                  value={sessionStorage.getItem("rateOfInterest")}
                />
              </Grid>
              <Grid item xs={12}  sm={6}>
                <TextField
                  required
                  fullWidth
                  name="loanAmount"
                  label="Loan Amount"
                  type="text"
                  id="loanAmount"
                  InputProps={{
                    readOnly: true,
                  }}
                  value={sessionStorage.getItem("loanAmount")}
                />
              </Grid>
              <Grid item xs={12}>
                  <TextField
                      required
                      fullWidth
                      name="vehicleName"
                      label="Vehicle Name"
                      type="text"
                      id="vehicleName"
                      onChange={handleChange}
                    />
                </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Apply for loan
            </Button>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
    </>
  );
}