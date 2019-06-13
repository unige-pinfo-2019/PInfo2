import { Component, Input } from '@angular/core';

import { Ad } from 'src/app/core';

import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-ad-preview',
  templateUrl: './ad-preview.component.html',
  styleUrls: ['./ad-preview.component.scss']
})
export class AdPreviewComponent {

  @Input() ad: Ad;
  @Input() thumbnailUrl: string;

  imagesUrl = `${environment.api_url}/image/`;

}
