import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpEventType} from '@angular/common/http';
import { text } from '@angular/core/src/render3';

/*Class regrouping all the services needed for posts*/
@Injectable()
export class PostsService{
  


 postsSubject = new Subject<any[]>();
   //lastUpdate = new Date().toLocaleString();


private posts=[];
  /*{
    id:1,
    title:'Post50000',
    description: 'Ceci est le post1',
    price:500,
    date:'2019-03-03',


  },
];*/
httpOptions = {
  headers: new HttpHeaders({
      'Content-Type': 'application/json',


  }),
};
httpOptionsImage={
  headers: new HttpHeaders(
    {
      'Content-Type': 'multipart/form-data',
    }
  ),
};
/*like(id:number){
  this.getPostById(id).likes+=1;


}
dislike(id:number){
  this.getPostById(id).likes-=1;


}*/
constructor(private httpClient: HttpClient){


}
emitPostSubject(){
  this.postsSubject.next(this.posts.slice());
}

getPostById(id:number){
  const post  = this.posts.find(
    (s)=>{
      return s.id===id;
    }
  );
  return post;
}


/*Prints the array posts for debbugin purposes*/
printPosts(){
  console.log('ArraytoString:');

for(let i=0;i<this.posts.length;i++){
console.log('id= '+ this.posts[i].id);
console.log('title= '+ this.posts[i].title);
console.log('Description= '+ this.posts[i].description);
console.log('price= '+ this.posts[i].price);
console.log('date= '+ this.posts[i].date);
console.log('Category= '+ this.posts[i].category);

}

}
addPost(title:string, description:string,price:number){
  const postObject = {
    //id:0,
    title: 'MonPost',
    description:'blablablalbalbalba',
    price: 50,
    categoryId:0,
   // caegory:'Livre',
  }
  postObject.title= title;
  postObject.description=description;
  postObject.price=price;
  //postObject.date=this.lastUpdate;
  

  this.posts.push(postObject);
  //this.printPosts();
  this.emitPostSubject();


  console.log('Enregistrement en cours... ');
  console.log(postObject);
  this.httpClient
  .post('http://localhost:10080/ad/',
  postObject,this.httpOptions).subscribe(
  ()=>{
    console.log('Enregistrement terminé ! ');
  },(error) => {
    console.log('Erreur  ! : '+ error);

  }

);
  }

  fileToServer(selecetdFile: File) {
    
/*curl -i -X POST -H 'Content-Type:multipart/form-data' -F 'file=@image.png' -F 'size=1' localhost:8080/image*/ 
    const uploadFormData = new FormData();
    const size = selecetdFile.size/(1024*1024);
  
    uploadFormData.append('file', selecetdFile);
    uploadFormData.append('size', '1'); 
    

    //print form Data
    


    this.httpClient
    .post('http://localhost:14080/image'
     ,uploadFormData,{ 
        responseType:'text',       
        reportProgress:true,   
        observe: 'events', 
      })
    .subscribe(
      (event)=>{
        if(event.type === HttpEventType.UploadProgress){
          console.log('Upload Progress: '+ Math.round(event.loaded/event.total*100)+ '%');
        }else if (event.type === HttpEventType.Response){
          console.log('Headers:'+ event.headers);
        }
        //const resp:any= response.toString();
        
        console.log(event);
        console.log('Image envoyé  ! ');
    },(error) => {
      console.log('Erreur  ! : '+ error);
  
    }
  );
  }

  getPosts(){
    console.log('chargement en cours... ');

    this.httpClient
    .get<any[]>('http://localhost:10080/ad')
    .subscribe(
      (response) => {
        this.posts = response;
        this.emitPostSubject();
      //this.printPosts();
      console.log('chargement réussi');

      },
      (error)=>{
        console.log('Erreur!:'+ error);
      }
    )
    //this.printPosts();
  }
  searchPost(searchTerm:string) {
   // console.log("searching on server for : " +searchTerm);
    this.httpClient.get<any[]>('http://localhost:11080/search/ad?q='+searchTerm).
    subscribe(
      (response)=>{
        //console.log("this is the response"+response);
        this.posts = response;
        this.emitPostSubject();
      },
      (error)=>{
        console.log('Erreur!:'+ error);
      }
      
    )
  }
  deletePosts(id:number){

    console.log('deleting all post of id: '+ id);
    this.httpClient
    .delete('http://localhost:10080/ad/'+id,this.httpOptions)
    .subscribe(
      () => {
        console.log('Tout a été supprimé')!
      },
      (error) => {
        console.log('Erreur: ! '+ error);
      }
    )
  this.posts.splice(this.posts.find(
      (s)=>{
        return s.id===id;
      }
),1);


    this.posts.pop();
    this.emitPostSubject();
  }

}
