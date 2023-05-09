package com.example.readerapp.screens.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.readerapp.R
import com.example.readerapp.components.FABContent
import com.example.readerapp.components.ReaderAppBar
import com.example.readerapp.components.TitleSection
import com.example.readerapp.model.MBook
import com.example.readerapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)) {
    
    Scaffold(topBar = {
                      ReaderAppBar(title = "A. Reader", navController = navController )
    },
        floatingActionButton =  {
        FABContent {
        }
        }){
           Surface(modifier = Modifier.fillMaxSize()) {
               // home content
             HomeContent(navController = navController)
               
           }
    }

        
    }


@Composable
fun HomeContent(navController: NavController){
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if(!email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email
            ?.split("@")?.get(0) else "N/A"
    Column(Modifier.padding(2.dp),
          verticalArrangement = Arrangement.Top){
     Row(modifier = Modifier.align(alignment = Alignment.Start)) {
         TitleSection(label = "Your reading  \n " + " activity right now...")
         Spacer(modifier = Modifier.fillMaxWidth(0.7f))
         Column{
             Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Profile",
              modifier = Modifier
                  .clickable {
                      navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                  }
                  .size(45.dp),
             tint = MaterialTheme.colors.secondaryVariant)
             Text(text = currentUserName.toString(),

                 modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.overline,
              color = Color.Red,
             fontSize = 15.sp,
             maxLines = 1,
             overflow = TextOverflow.Clip)
             Divider()
             
         }
         
     }
              ListCard()

    }

}

@Preview
@Composable
fun RoundedButton(
    label:String = "Reading",
    radius:Int = 29,
    onPress:() -> Unit = {}){
    Surface(modifier = Modifier.clip(RoundedCornerShape(
        bottomEndPercent = radius,
        topStartPercent = radius)),
    color = Color(0xFF92CBDF)) {
        Column(modifier = Modifier
            .width(90.dp)
            .heightIn(40.dp)
            .clickable { onPress.invoke() },
             verticalArrangement = Arrangement.Center,
             horizontalAlignment = Alignment.CenterHorizontally) {
         Text(text = label, style = TextStyle(color = Color.White, fontSize = 15.sp))
        }

    }

}

@Preview
@Composable
fun ListCard(book:MBook = MBook("shsjhsh", "Running", "Me and You", "hello world"),
             onPressDetails:(String) -> Unit = {}) {

    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp
    Card(shape = RoundedCornerShape(29.dp),
    backgroundColor = Color.White,
    elevation = 6.dp,
    modifier = Modifier
        .padding(16.dp)
        .height(242.dp)
        .width(200.dp)
        .clickable {
            onPressDetails.invoke(book.title.toString())
        }){
        Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
          horizontalAlignment = Alignment.Start){
         Row(horizontalArrangement = Arrangement.Center){
             AsyncImage(
                 model = "http://books.google.com/books/content?id=bPJnCEC0JkIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                contentDescription = null,
                   modifier = Modifier
                       .height(140.dp)
                       .width(100.dp)
                       .fillMaxSize()
                       .padding(4.dp))
             Spacer(modifier = Modifier.width(50.dp))
             Column(modifier = Modifier.padding(top = 25.dp),
                 verticalArrangement = Arrangement.Center,
                 horizontalAlignment = Alignment.CenterHorizontally){
                 Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = "Fav Icon",
                    modifier = Modifier.padding(bottom = 1.dp))
                 BookRating(score = 3.5)
             }
         }

            Text(text = "Book Title", modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                 overflow = TextOverflow.Ellipsis)
            Text(text = "Authors: All...", modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption)
        }


        Row(horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom) {

                RoundedButton(label = "Reading", radius = 70)

            }
        }


    }

@Composable
fun BookRating(score: Double = 4.5) {
    Surface(
        modifier = Modifier
            .height(70.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(56.dp),
        elevation = 6.dp,
        color = Color.White) {
        Column(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Filled.StarBorder, contentDescription = " Star Border",
            modifier = Modifier.padding(3.dp))
            Text(text = score.toString(), style = MaterialTheme.typography.subtitle1)
            
        }

    }


}


@Composable
fun ReadingRightNowArea(books:List<MBook>, navController: NavController){

}

