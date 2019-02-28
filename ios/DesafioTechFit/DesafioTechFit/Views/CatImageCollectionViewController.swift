//
//  CatImageCollectionViewController.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 22/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit
import Alamofire

private let reuseIdentifier = "Cell"

class CatImageCollectionViewController: UICollectionViewController, UIViewControllerTransitioningDelegate {
    
    // MARK: - Properties
    var catImages: [CatImage] = []
    var page = 1;
    var segueDetail = "segueDetail"
    var transition = CircularTransition()
    
    // MARK: - Main Methods
    override func viewDidLoad() {
        super.viewDidLoad()
        NotificationCenter.default.addObserver(self, selector: #selector(applicationDidBecomeActive), name: UIApplication.didBecomeActiveNotification, object: nil)
        loadCatsAlamo()
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setTheme()
    }

    @objc func applicationDidBecomeActive(notification: NSNotification) {
        setTheme()
    }

    // MARK: - UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }

    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return catImages.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CatImageCollectionViewCell
        cell.prepare(with: self.catImages[indexPath.row])
        
        return cell
    }
    
    // MARK: UICollectionViewDelegate
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        performSegue(withIdentifier: segueDetail, sender: catImages[indexPath.row])
        return true
    }
    
    override func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        if indexPath.row == catImages.count - 1 {
            print("loading more....")
            page = page + 1
            self.loadCatsAlamo()
        }
    }
    
    // MARK: - Methods
    func setTheme() {
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.collectionView.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        self.navigationController?.navigationBar.barTintColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        self.view.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        self.navigationController?.navigationBar.largeTitleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let catDetailVC = segue.destination as! CatDetailViewController
        catDetailVC.transitioningDelegate = self
        catDetailVC.modalPresentationStyle = .custom
        catDetailVC.catImage = sender as! CatImage
    }
    
    func loadCatsAlamo(){
        let pathUrl = "\(REST.basePath)search?limit=60&page=\(page)&order=Desc"
        Alamofire.request(pathUrl, method: .get, parameters: nil, encoding: JSONEncoding(), headers: nil)
            .validate()
            .responseJSON { (response) in
                guard let data = response.data else { return }
                let _catImages = try! JSONDecoder().decode([CatImage].self, from: data)
                for _image in _catImages {
                    self.catImages.append(_image)
                }
                self.collectionView.reloadData()
        }
    }
    
    // MARK: - Circle Animation
    func animationController(forPresented presented: UIViewController, presenting: UIViewController, source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        transition.transitionMode = .present
        transition.startingPoint = collectionView.center
        transition.circleColor =  UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)!
        
        return transition
    }
    
    func animationController(forDismissed dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        transition.transitionMode = .dismiss
        transition.startingPoint = collectionView.center
        transition.circleColor =  UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)!
        return transition
    }
}
