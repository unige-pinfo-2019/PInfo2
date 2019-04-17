import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

/*Class regrouping all the services needed for posts*/
@Injectable()
export class PostsService{

 postsSubject = new Subject<any[]>();
   lastUpdate = new Date().toLocaleString();


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
    id:0,
    title: 'MonPost',
    description:'blablablalbalbalba',
    date:this.lastUpdate.toString(),
    price: 50,
    category:'Livre',
  };
  postObject.title= title;
  postObject.description=description;
  //postObject.price=price;

  this.posts.push(postObject);
  this.printPosts();
  this.emitPostSubject();


  console.log('Enregistrement en cours... ');
  this.httpClient
  .post('http://localhost:8080/ad/',
  postObject,this.httpOptions).subscribe(
  ()=>{
    console.log('Enregistrement terminé ! ');
  },(error) => {
    console.log('Erreur  ! : '+ error);

  }

);
  }



  getPosts(){
    console.log('chargement en cours... ');

    this.httpClient
    .get<any[]>('http://localhost:8080/ad')
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
    this.printPosts();
  }
  deletePosts(id:number){

    console.log('deleting all post of id: '+ id);
    this.httpClient
    .delete('http://localhost:8080/ad/'+id,this.httpOptions)
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
