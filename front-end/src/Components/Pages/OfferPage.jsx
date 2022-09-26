import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import PercentIcon from '@mui/icons-material/Percent';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import UserNavbar from './navigation/UserNavbar';
import { Card, CardActionArea, CardContent, CardMedia } from '@mui/material';
import { useState } from 'react';
import axios from 'axios';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';



function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props} position='static'>
      {'Copyright © '}
      <Link color="inherit" href="/about">
        Lending Mock
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();



export default function OfferPage() {
  const navigate = useNavigate()
  const baseUrl = "http://localhost:8084/v1/offer"
  const [data, setData] =useState([])
  const [temp, setTemp] = useState()

  useEffect(() => {
    axios.get(baseUrl+"/"+sessionStorage.getItem("phoneNumber"))
    .then((response)=>{
      setData(response.data)
      console.log(response.data)
    })
  }, [])

  const handleClick = (loan) =>{   
      sessionStorage.setItem("loanName", loan.loanName)
      sessionStorage.setItem("rateOfInterest", loan.rateOfInterest)
      sessionStorage.setItem("loanAmount", loan.loanAmount)
      sessionStorage.setItem("timePeriod", loan.timePeriod)
      setTemp(loan.loanName)
      console.log(temp)
    if(temp==='Housing loan'){
      navigate("/housing")
    }
    else if(temp==="Education loan"){
      console.log("*")
      navigate("/education")
    }
    else if(temp==="Vehicle loan"){
      navigate("/vehicle")
    }
    else if(temp==="Business loan"){
      navigate("/business")
    }
    else{
      console.log("Else:",temp)
    }
  }
  

  return (
    <>
    <UserNavbar/>
    <ThemeProvider theme={theme}>
      <Container component="main">
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
            <PercentIcon/>
          </Avatar>
          <Typography component="h1" variant="h5">
            Offers For You
          </Typography>
          <Box  sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              {data.map((x)=>
              <Grid item xs={12}  sm={6}>
                <Card sx={{ maxWidth: 345 }}>
                    <CardActionArea onClick={(e) => handleClick(x)}>
                        <CardMedia
                        component="img"
                        height="140"
                        image="https://i1.wp.com/mvdna.com/wp-content/uploads/2018/03/SpecialOffer.png"
                        alt="green iguana"
                        />
                        <CardContent>
                        <Typography gutterBottom variant="h5" component="div">
                            {x.loanName}
                        </Typography>
                        <Typography variant="body2" color="text.secondary" component="ul" align='justify'>
                            
                                <li>Type Of Loan: {x.loanName}</li>
                                <li>Offered Intrest Rate: {x.rateOfInterest}% Compound Intrest </li>
                                <li>Amount Loaned: {x.loanAmount}</li>
                                <li>Tenure period of loan: {x.timePeriod} years  </li>
                            
                        </Typography>
                        <Typography  variant="body3" color="text.secondary" sx={{mt:3}}>
                            *Terms and conditions Apply
                        </Typography>
                        </CardContent>
                    </CardActionArea>
                    </Card>
              </Grid>
              )}
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 20 }} />
      </Container>
    </ThemeProvider>
    </>
  );
}