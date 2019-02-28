//
//  FavoritesTableViewController.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 27/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit
import CoreData

class FavoritesTableViewController: UITableViewController, UIViewControllerTransitioningDelegate {

    //MARK: - Properties
    var fetchedResultController: NSFetchedResultsController<Cat>?
    var segueDetail = "segueDetail"
    var transition = CircularTransition()
    
    //MARK: - Main Methods
    override func viewDidLoad() {
        super.viewDidLoad()
        loadCats()
        
         NotificationCenter.default.addObserver(self, selector: #selector(applicationDidBecomeActive), name: UIApplication.didBecomeActiveNotification, object: nil)
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setTheme()
    }
    

    // MARK: - Methods
    @objc func applicationDidBecomeActive(notification: NSNotification) {
        setTheme()
    }
    
    private func loadCats(){
        
        let fetchRequest: NSFetchRequest<Cat> = Cat.fetchRequest()
        let sortDescriptor = NSSortDescriptor(keyPath: \Cat.id, ascending: true)
        //let sortDescriptorDate = NSSortDescriptor(key: #keyPath(Movie.releaseDate), ascending: false)
        fetchRequest.sortDescriptors = [sortDescriptor]
        fetchedResultController = NSFetchedResultsController(fetchRequest: fetchRequest, managedObjectContext: context, sectionNameKeyPath: nil, cacheName: nil)
        fetchedResultController?.delegate = self
        do {
            try fetchedResultController?.performFetch()
        } catch {
            print(error)
        }
    }
    
    func setTheme() {
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.tableView.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        self.navigationController?.navigationBar.barTintColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        self.view.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        self.navigationController?.navigationBar.largeTitleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
    }
    
    // MARK: - Table view data source
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (fetchedResultController?.fetchedObjects?.count ?? 0 ) > 0 {
            TableViewHelper.hideEmpty(in: self)
            return fetchedResultController?.fetchedObjects?.count ?? 0
        } else {
            TableViewHelper.showEmpty(message: "Sem Favoritos 🙀 ", in: self)
            return 0
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as? FavoriteTableViewCell else { return UITableViewCell() }
        guard let cat = fetchedResultController?.object(at: indexPath) else { return cell }
        cell.prepare(with: cat)
        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let cat = fetchedResultController?.object(at: indexPath) else { return }
        performSegue(withIdentifier: segueDetail, sender: cat)
    }
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
       
        let catDetailVC = segue.destination as! CatDetailViewController
        catDetailVC.transitioningDelegate = self
        catDetailVC.modalPresentationStyle = .custom
        
        let cat = sender as! Cat
        var catImage = CatImage()
        catImage.id = cat.id!
        catDetailVC.catImage = catImage
        
    }
    
    // MARK: - Circle Animation
    func animationController(forPresented presented: UIViewController, presenting: UIViewController, source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        transition.transitionMode = .present
        transition.startingPoint = tableView.center
        transition.circleColor =  UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)!
        
        return transition
    }
    
    func animationController(forDismissed dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        transition.transitionMode = .dismiss
        transition.startingPoint = tableView.center
        transition.circleColor =  UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)!
        
        return transition
    }

}

extension FavoritesTableViewController: NSFetchedResultsControllerDelegate {
    func controller(_ controller: NSFetchedResultsController<NSFetchRequestResult>, didChange anObject: Any, at indexPath: IndexPath?, for type: NSFetchedResultsChangeType, newIndexPath: IndexPath?) {
        tableView.reloadData()
    }
}
