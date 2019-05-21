import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpEventType} from '@angular/common/http';
import { text } from '@angular/core/src/render3';
import { UserService } from './user.service';

import { environment } from '../../environments/environment';

/*Class regrouping all the services needed for posts*/
@Injectable()
export class PostsService{
  searchCategory(catId: number) {
    //todo: make a search byCategory
    this.httpClient.get<any[]>(environment.ad_url)
    .subscribe(

      (response)=>{
      this.posts=response.filter(
        post=>{
          return post.categoryId === catId;
        }
      );
      this.emitPostSubject()
    },(error)=>{
      console.log(error);
    }
    );
  }

 postsSubject = new Subject<any[]>();
 //this array contains the posts availible on the ui
public posts=[];
public singlePost={
  };
private localUrl= 'http://localhost:';
private serverUrl = 'http://pinfo2.unige.ch:';


private imagePort='14080/image';
private adPort='15080/ad';

public imageId =[];

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

constructor(private httpClient: HttpClient,
            private userService:UserService){

}
emitPostSubject(){
  this.postsSubject.next(this.posts.slice());
}

getPostById(id:number){
  console.log('chargement postById... ');

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
  console.log('Category= '+ this.posts[i].categoryId);

}

}
addPost(title:string, description:string,price:number,categoryId:number,imageIds:number[],userId:number){

        const postObject = {
    //id:0 The backend decides of the postId,but the field does exists
    title: '',
    description:'',
    price: 0,
    categoryId:0,
    userId:0,
    imageIds:[],
  }
  postObject.title= title;
  postObject.description=description;
  postObject.price=price;
  postObject.categoryId=categoryId;
  postObject.imageIds=imageIds;
  postObject.userId=userId;

  this.posts.push(postObject);

  this.emitPostSubject();

  console.log('Enregistrement en cours... ');
  console.log(postObject);
  this.httpClient
  .post(environment.ad_url,
  postObject,this.httpOptions).subscribe(
  ()=>{
    console.log('Enregistrement terminé ! ');
    this.imageId=[];
  },(error) => {
    console.log('Erreur  ! : '+ error);

  }

);
  }

   async fileToServer(selecetdFile: File) {

    const uploadFormData = new FormData();
    const size = selecetdFile.size/(1024*1024);

    uploadFormData.append('file', selecetdFile);
    uploadFormData.append('size', '1');

    await this.httpClient
    .post(this.serverUrl+this.imagePort
     ,uploadFormData,{
        responseType:'text',
        reportProgress:true,
        observe: 'events',
      })
    .subscribe(
      (event)=>{
        var imageId2;
        if(event.type === HttpEventType.UploadProgress){
          console.log('Upload Progress: '+ Math.round(event.loaded/event.total*100)+ '%');

        }else if (event.type === HttpEventType.Response){
          const imageUrl = event.headers.get('location'); //gets the url returned by the POST
          imageId2 =  parseInt(imageUrl.substring(imageUrl.lastIndexOf('/')+1,imageUrl.length)); //extract Id from the url
          this.imageId.push(imageId2);
          this.emitPostSubject();
          console.log(this.imageId);
        }
    },(error) => {
      console.log('Erreur  ! : '+ error);
    }
  );
  }
  getPosts(){
    console.log('chargement en cours... ');

    this.httpClient
    .get<any[]>(environment.ad_url)
    .subscribe(
      (response) => {
        response.reverse();
        this.posts = response;
        this.emitPostSubject();
      console.log('chargement réussi');

      },
      (error)=>{
        console.log('Erreur!:'+ error);
      }
    )
  }
  getImage(imageId:number){
    console.log('loading image from server..');
  }
  searchPost(searchTerm:string) {
    console.log("searching on server for : " +searchTerm);
    this.httpClient.get<any[]>(environment.search_url+searchTerm).
    subscribe(
      (response)=>{
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
