import { AppBar, Button, IconButton, Stack, Toolbar, Typography } from '@mui/material'
import React from 'react'
import { Link } from 'react-router-dom'
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';

function DefaultNavbar() {
return (
    <AppBar position='static'>
        <Toolbar>
            <IconButton size='large' edge='start' color='inherit'>
                <AutoAwesomeIcon/>
            </IconButton>
            <Typography variant='h6' component='div' sx={{flexGow: 1}}>
                Lending Mock Application
            </Typography>
            <Stack direction='row' spacing={2} sx={{ml:'auto'}}>
                <Button
                color='inherit' 
                component={Link}
                to='/'
                 >
                    Login
                </Button>
                <Button color='inherit'
                    component={Link}
                    to='/register' 
                 >
                    Register</Button>
            </Stack>
        </Toolbar>
    </AppBar>
  )
}

export default DefaultNavbar