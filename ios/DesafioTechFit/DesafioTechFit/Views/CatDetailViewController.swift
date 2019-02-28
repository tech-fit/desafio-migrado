//
//  CatDetailViewController.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 23/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit
import CoreData

class CatDetailViewController: UIViewController {

    //MARK: - IBOutlets
    @IBOutlet weak var ivCat: UIImageView!
    
    @IBOutlet weak var btnClose: UIButton!
    @IBOutlet weak var btnLike: UIButton!
    
    //MARK: - Properties
    var cat: Cat!
    let imageLiked = UIImage(named: "liked")
    let imageUnliked = UIImage(named: "unliked")
    var liked = false
    var catImage = CatImage()
    
    //MARK: - Main Methods
    override func viewDidLoad() {
        super.viewDidLoad()
        loadCat()
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .default
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.view.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        
        setupButton(for: btnClose)
        setupButton(for: btnLike)
        
        
        if (cat != nil) {
            btnLike.setImage(imageLiked, for: .normal)
            liked = true
            if let photoData = cat.image {
                ivCat.image = UIImage(data: photoData)
            }
        }
        else {
            ivCat.loadImageUsingUrlString(urlString: catImage.url)
            if liked {
                btnLike.setImage(imageLiked, for: .normal)
            } else {
                btnLike.setImage(imageUnliked, for: .normal)
            }
        }
        
        
    }
    
    // MARK: - Methods
    
    func loadCat(){
        let fetchRequest: NSFetchRequest<Cat> = Cat.fetchRequest()
        let predicate = NSPredicate(format: "id = %@", catImage.id)
        fetchRequest.predicate = predicate
        do {
            let cats = try context.fetch(fetchRequest)
            if  cats.count != 0 {
                cat = cats[0]
            }
        } catch {
            print(error)
        }
    }
    
    func setupButton(for button: UIButton) {
        button.backgroundColor = UIColor(named: ThemeColorsButton.allValues[UserDefaultsManager.themeNumber()].rawValue)
        button.layer.cornerRadius = 15
        button.clipsToBounds = true
        button.layer.borderColor = UIColor(named: "darkBackground")!.cgColor
        button.layer.borderWidth = 1.5
    }
    
    // MARK: - IBActions
    
    @IBAction func btnCloseClick(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func btnLikeClick(_ sender: Any) {
        
        liked = !liked
        
        if liked {
            if cat == nil {
                cat = Cat(context: context)
            }
            cat.id = catImage.id
            if let photoData = ivCat.image?.pngData() {
                cat.image = photoData
            }
            btnLike.setImage(imageLiked, for: .normal)
        } else {
            btnLike.setImage(imageUnliked, for: .normal)
            context.delete(cat)
        }
        saveContext()
        
    }

}
