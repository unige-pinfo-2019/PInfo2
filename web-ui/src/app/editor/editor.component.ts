import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, FormArray, ValidationErrors } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Ad, AdService, CategoryService, UserService, Errors } from '../core';
import { environment } from '../../environments/environment';

import { ImageUploaderOptions, FileQueueObject } from 'ngx-image-uploader';

@Component({
    selector: 'app-editor-page',
    templateUrl: './editor.component.html',
    styleUrls: [ './editor.component.scss' ]
})
export class EditorComponent implements OnInit {
    ad: Ad = {} as Ad;
    adForm: FormGroup;
    formatedCategories: any[];
    errors: Object = {};
    isSubmitting = false;
    imageServiceUrl = `${environment.api_url}/image/`
    options: ImageUploaderOptions = {
        thumbnailHeight: 200,
        thumbnailWidth: 200,
        uploadUrl: this.imageServiceUrl,
        allowedImageTypes: ['image/png', 'image/jpeg'],
        maxImageSize: 3
    };

    constructor(
        private adService: AdService,
        private categoryService: CategoryService,
        private route: ActivatedRoute,
        private router: Router,
        private fb: FormBuilder
    ) {
        // use the FormBuilder to create a form group
        this.adForm = this.fb.group({
            title: ['', [Validators.required, Validators.minLength(5)]],
            description: ['', Validators.maxLength(2500)],
            price: [null, [Validators.required, Validators.min(0)]],
            categoryId: [null, Validators.required],
            imageIds: this.fb.array([])
        });
    }

    ngOnInit() {
        this.categoryService.getFormatedCategories().subscribe(
            data => this.formatedCategories = data
        );
        // If there's an Ad prefetched, load it
        this.route.data.subscribe(({ ad }) => {
            if (ad) {
                this.ad = ad;
                this.adForm.patchValue(ad);
            }
        });
    }

    onClickCategory(event) {
        this.adForm.patchValue(
            {categoryId: event}
        );
    }

    get imageIds() {
        return this.adForm.get('imageIds') as FormArray;
    }

    onUpload(file: FileQueueObject) {
        const url = file.response.headers.get('location');
        const imageId = url.split("/").slice(-1)[0];
        this.imageIds.push(this.fb.control(+imageId));
    }

    checkErrors() {
        var allErrors = { errors: {} };
        Object.keys(this.adForm.controls).forEach(key => {
            const controlErrors: ValidationErrors = this.adForm.get(key).errors;
            if (controlErrors != null) {
                Object.keys(controlErrors).forEach(keyError => {
                    switch (keyError) {
                        case 'required': {
                            allErrors.errors[key] = ['is required.'];
                            break;
                        }
                        case 'minlength': {
                            allErrors.errors[key] = ['should at least contains ' +
                                controlErrors[keyError].requiredLength + ' characters.'];
                            break;
                        }
                        case 'maxlength': {
                            allErrors.errors[key] = ['can at most contains ' +
                                controlErrors[keyError].requiredLength + ' characters.'];
                            break;
                        }
                        case 'min': {
                            allErrors.errors[key] = ['cannot be lower than ' +
                                controlErrors[keyError].min + '.'];
                        }
                    }   
                });
            }
        });
        this.errors = allErrors;
    }

    submitForm() {
        if (!this.adForm.valid) {
            this.checkErrors();
            return;
        }

        this.isSubmitting = true;

        // update the model
        this.updateAd(this.adForm.value);

        // post the changes
        this.adService.save(this.ad).subscribe(
            (ad: Response) => this.router.navigateByUrl('/ad/' + ad.headers.get('Location').split("/").slice(-1)[0]),
            err => {
                this.errors = err;
                this.isSubmitting = false;
            }
        );
    }

    updateAd(values: Object) {
        Object.assign(this.ad, values);
    }
}